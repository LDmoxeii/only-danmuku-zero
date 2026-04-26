# Cap4k Pipeline Zero Dogfood Generation Inventory

## Input Baseline

本轮极限 dogfood 不再使用单一 active `iterate/drawing_board.json` 作为 design 输入。

实际输入：

- `codegen/design/raw-drawing-board.json`：来自旧插件导出的 `only-danmuku/design/drawing_board.json`，覆盖大部分历史设计输入。
- `iterate/drawing_board.json`：只作为增量补充来源。
- `codegen/design/design.json`：标准化后的最终 design 输入。
- `codegen/enum-manifest/shared-enums.json`：共享枚举输入，用于从零生成 `UserType`、`PostType`、`EncryptMethod`。
- `docs/dogfood/h2/only_danmuku.h2.schema.sql`：H2 DB aggregate 输入。

标准化规则：

- `cmd` -> `command`
- `qry` -> `query`
- `cli` -> `client`
- `payload` -> `api_payload`
- `de` -> `domain_event`
- 已带完整包根的 design package 会转成相对 package，避免生成到 `.../edu/only4/...` 嵌套路径。
- design 字段中的聚合实体、本地枚举、共享枚举短类型会转成生成器产物 FQN。
- `[]` 嵌套字段写法会转成当前 pipeline 支持的单层 nested field 写法。
- `domain_event.requestFields.entity` 会被丢弃，聚合实体参数由新 pipeline 的领域事件固定语义生成。

## Current Generation Result

验证命令：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kPlan
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kGenerate
```

结果：

- `cap4kPlan` 成功。
- `cap4kGenerate` 成功。
- plan items：854。
- distinct output：854。
- 实际 Kotlin 文件：854。

按生成族统计：

| Family | Files |
| --- | ---: |
| aggregate entity / factory / specification / wrapper | 91 |
| aggregate enum | 22 |
| aggregate enum translation | 22 |
| aggregate repository | 21 |
| aggregate schema | 28 |
| aggregate unique validator | 17 |
| design api payload | 98 |
| design client | 27 |
| design client handler | 27 |
| design command | 91 |
| design domain event | 104 |
| design domain event handler | 104 |
| design query / unique query | 101 |
| design query handler / unique handler | 101 |

编译验证：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache compileKotlin
```

结果：

- `compileKotlin` 成功。
- zero host 当前保留最小手写边界 `UserMessageExtend`。
- zero host 当前显式提供生成代码需要的外部依赖：`engine-json`、`engine-common`、`engine-translation`、`spring-web`。

## Directly Generatable

当前 pipeline 已能从 zero host 直接生成：

- DB aggregate 基础产物：entity、schema、repository、本地 enum。
- DB aggregate 可选产物：factory、specification、wrapper、unique query、unique query handler、unique validator、enum translation。
- enum-manifest 共享枚举：`UserType`、`PostType`、`EncryptMethod`。
- design command/query/client。
- design query handler/client handler。
- design api payload。
- design domain event/domain event handler。

## Manual Or External

这些不应该被当前 cap4k generator 直接生成，属于手写、外部依赖或其他工具产物：

- Web controller、真实接口编排、认证鉴权 glue code。
- 生成 handler/subscriber 内部的业务实现，当前模板只能给出结构和 TODO。
- 项目级共享基础设施：审计基类、错误码、异常封装、Spring 配置、Jimmer 配置。
- 自定义值对象和序列化类型，例如 `UserMessageExtend`。
- 外部引擎类型，例如 `com.only.engine.enums.CaptchaChannel`。
- KSP 或其他工具生成的 DTO/metadata。
- 测试代码。

## Unsupported Design Entries

当前标准化脚本跳过 7 个旧 design entry，已落到 `codegen/design/skipped-design.json`。

跳过原因：

- 多层 nested field：当前 pipeline 只支持一层 nested field。
- 递归 tree response：旧输入使用 `List<Response>` 或 `List<Item>` 表达树结构，当前 design renderer 不支持。
- 旧 Item response 模型：旧 payload/query 模板可以生成 `Item` 根响应模型，当前新模板固定 Request/Response 模型。

这 7 个 entry 不是手写业务代码，而是 pipeline 设计能力缺口，需要后续单独评估是否扩展。
