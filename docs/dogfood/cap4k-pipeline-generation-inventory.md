# Cap4k Pipeline Zero Dogfood Generation Inventory

## Input Baseline

当前 `only-danmuku-zero` 是手动复现输入集，不保留 `cap4kBootstrap` 或 `cap4kGenerate` 生成出来的四个子模块源码。

你可以从这个状态开始手动执行：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kBootstrap
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kPlan
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kGenerate
```

生成后可以执行 contract 审计：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kPlan
pwsh -NoProfile -ExecutionPolicy Bypass -File .\docs\dogfood\audit-design-contracts.ps1
```

该审计只检查 `command` / `query` / `client` 的 application contract。它从 `build/cap4k/plan.json` 读取 `cap4kPlan` 的实际 `outputPath`，再校验 `*Cmd`、`*Qry`、`*Cli` 的 `Request` / `Response` 结构是否与 design 生成上下文一致。

当前迁移阶段该命令允许失败，因为失败结果就是待修复的 contract drift 清单。该审计不要求产物必须位于 `build/generated`；如果 plan 决定输出到 `src/main/kotlin`，就按该路径做结构校验。

当前 drift 修复清单见 `docs/dogfood/cap4k-pipeline-contract-drift-backlog.md`。

本轮极限 dogfood 不再使用单一 active `iterate/drawing_board.json` 作为 design 输入。

实际输入：

- `codegen/design/raw-drawing-board.json`：来自旧插件导出的 `only-danmuku/design/drawing_board.json`，覆盖大部分历史设计输入。
- `iterate/drawing_board.json`：只作为增量补充来源，当前包含少量旧导出缺失的 client/query/payload，以及 7 个可由 `designValidator` 生成空骨架的简单字段级 validator。
- `codegen/design/design.json`：标准化后的最终 design 输入。
- `docs/dogfood/normalize-design-input.ps1`：标准化脚本，要求 PowerShell 7+ (`pwsh`) 以保证 UTF-8 和 JSON 格式稳定。
- `codegen/enum-manifest/shared-enums.json`：共享枚举输入，用于从零生成 `UserType`、`PostType`、`EncryptMethod`。
- `docs/dogfood/h2/only_danmuku.h2.schema.sql`：H2 DB aggregate 输入。
- `codegen/bootstrap-templates/bootstrap/module/*.peb`：基于 `only-danmuku` 依赖形态整理的模块 build.gradle bootstrap 覆盖模板。
- `codegen/bootstrap-templates/aggregate/unique_query_handler.kt.peb`：基于 Jimmer `KSqlClient.exists` 的唯一性查询 handler 覆盖模板。
- `codegen/bootstrap-slots/root/**`：来自 `only-danmuku` 的 `buildSrc` convention 和 `gradle/libs.versions.toml`。
- `codegen/bootstrap-slots/start-resources/**`：来自 `only-danmuku-start/src/main/resources` 的 `application.yml`、`logback.xml`、frame init migration。
- `codegen/bootstrap-slots/domain-package/**`：来自 `only-danmuku-domain` 且不由当前 generator 同路径产出的稳定源码补齐层。
- `codegen/bootstrap-slots/application-package/**`：来自 `only-danmuku-application` 且不由当前 generator 同路径产出的稳定源码补齐层。
- `codegen/bootstrap-slots/adapter-package/**`：来自 `only-danmuku-adapter` 且不由当前 generator 同路径产出的稳定源码补齐层。

slot 边界：

- 凡是能通过 `root`、`modulePackage(role)`、`moduleResources(role)` 稳定投放的输入文件，都优先纳入 bootstrap slot。
- slot 不是“最小手写边界”，而是复现真实项目时的静态补齐层。
- 如果某个目标路径会被 `cap4kGenerate` 同路径产出，不能靠 bootstrap slot 保真；因为推荐流程是先 bootstrap 再 generate，且当前 generate conflict policy 是 `OVERWRITE`。
- 同路径 generator 产物和真实项目实现存在差异时，应记录为 generator 能力缺口、模板缺口或后续手写实现，不放进 bootstrap slot 伪装通过。
- 复杂 validator 例外：当前 `designValidator` 只支持字段/参数级 `Long` 空骨架，class-level、自定义注解参数或特殊 valueType 的 validator 仍保留在 slot，保证完整插件流程后项目可编译，同时在 backlog 标记为 generator 能力缺口。

当前 slot/template 规模：

| Area | Files |
| --- | ---: |
| bootstrap module templates | 4 |
| aggregate generation template overrides | 1 |
| root slot | 4 |
| start resources slot | 3 |
| domain package slot | 8 |
| application package slot | 61 |
| adapter package slot | 38 |

标准化规则：

- `cmd` -> `command`
- `qry` -> `query`
- `cli` -> `client`
- `payload` -> `api_payload`
- `de` -> `domain_event`
- 简单字段级 validator 作为 `tag = validator` 增量输入，走默认 `application.validators` 包根。
- 已带完整包根的 design package 会转成相对 package，避免生成到 `.../edu/only4/...` 嵌套路径。
- design 字段中的聚合实体、本地枚举、共享枚举短类型会转成生成器产物 FQN。
- `[]` 嵌套字段写法会转成当前 pipeline 支持的单层 nested field 写法。
- `domain_event.requestFields.entity` 会被丢弃，聚合实体参数由新 pipeline 的领域事件固定语义生成。

## Previous Generation Result

以下是清理为输入集之前的上一轮验证结果，用于说明当前输入材料曾经覆盖到的生成规模。

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
- zero host 当时临时保留 `UserMessageExtend`；当前已改为通过 bootstrap slot 注入。
- zero host 当时显式提供生成代码需要的外部依赖：`engine-json`、`engine-common`、`engine-translation`、`spring-web`；当前已改为通过 bootstrap module template 覆盖注入。

## Directly Generatable

当前 pipeline 已能从 zero host 直接生成：

- DB aggregate 基础产物：entity、schema、repository、本地 enum。
- DB aggregate 可选产物：factory、specification、wrapper、unique query、unique query handler、unique validator、enum translation。
- enum-manifest 共享枚举：`UserType`、`PostType`、`EncryptMethod`。
- design command/query/client。
- design query handler/client handler。
- design api payload。
- design domain event/domain event handler。
- design simple field-level validator 空骨架。

## Slot Supplement

当前 bootstrap slot 已补入真实 `only-danmuku` 中适合通过路径投放的稳定文件：

- 根工程 build logic：`buildSrc` convention、version catalog。
- start 资源：`application.yml`、`logback.xml`、frame init migration。
- domain 补齐：审计基类、Schema 辅助、业务错误、`UserMessageExtend`、领域端口。
- application 补齐：配置属性、常量、复杂业务 validator、查询模型、因多层 nested 暂不支持而跳过的 command。
- adapter 补齐：controller、payload、Spring/Jimmer/domain context 配置、事件拦截器、因递归/旧 Item response 暂不支持而跳过的 query handler。

## Not Covered By Slot Or Current Generator

这些内容不通过当前 bootstrap slot 承担：

- `cap4kGenerate` 会同路径产出的文件实现体差异，例如 handler/subscriber 内部真实业务代码；bootstrap slot 会在 generate 后被覆盖，不能作为保真机制。
- 已能生成空骨架的普通 query handler、domain event subscriber、普通 command 的真实业务实现后续从真实项目复制，不通过 bootstrap slot 伪装生成。
- 递归/旧 Item response 和多层 nested model 当前属于明确 generator 能力缺口，允许用 bootstrap slot 补齐对应 skipped design 文件。
- 简单字段级 validator、`Unique*` validator、enum translation、共享 enum、空 subscriber 骨架由当前 generator 生成，不放入 bootstrap slot。
- 外部引擎依赖类型，例如 `com.only.engine.enums.CaptchaChannel`，由依赖提供而不是项目源码输入提供。
- KSP 或其他工具生成的 DTO/metadata。
- 测试代码。

## Unsupported Design Entries

当前标准化脚本跳过 7 个旧 design entry，已落到 `codegen/design/skipped-design.json`。

跳过原因：

- 多层 nested field：当前 pipeline 只支持一层 nested field。
- 递归 tree response：旧输入使用 `List<Response>` 或 `List<Item>` 表达树结构，当前 design renderer 不支持。
- 旧 Item response 模型：旧 payload/query 模板可以生成 `Item` 根响应模型，当前新模板固定 Request/Response 模型。

这 7 个 entry 不是手写业务代码，而是 pipeline 设计能力缺口，需要后续单独评估是否扩展。
