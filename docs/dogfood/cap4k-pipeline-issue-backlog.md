# Cap4k Pipeline Dogfood Issue Backlog

## 记录规则

本文件只记录 dogfood 过程中暴露的问题和判断，不直接代表必须修复。

兼容性问题默认等待评估；能通过 H2 测试材料绕开的，先在测试材料中绕开，不阻断后续验证。

## Issues

### [blocker] [db-source/mysql] MySQL `DatabaseMetaData` 读取串库导致 aggregate plan 误判

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

复现条件：

`sources.db.url` 指向 live MySQL `jdbc:mysql://127.0.0.1:3306/only_danmuku...`，`schema = only_danmuku`。

实际结果：

`build/cap4k/plan.json` 的 `diagnostics.aggregate.discoveredTables` 出现其他 schema 的表，例如 `easylive.category_info`、`mysql.user`、其他库里的 `role`，并出现重复表名。

随后 aggregate 失败：

```text
db table role is unsupported for aggregate generation: composite_primary_key
```

判断：

真实 `only_danmuku` 业务表没有复合主键阻塞。误判来自 MySQL JDBC metadata 的 catalog/schema 语义差异，新 source 使用 `getTables(null, schema, ...)` 时没有把 MySQL database 当 catalog 限定。

处理建议：

如果新插件要直接支持 MySQL live introspection，需要修 source-db 的 metadata scope；如果短期只走 H2 dogfood，可以暂不处理。

### [medium] [db-config] `sources.db.password` 必须非空，H2 空密码无法直接配置

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

复现条件：

H2 配置使用：

```kotlin
username.set("sa")
password.set("")
```

实际结果：

```text
sources.db.password is required when db is enabled.
```

判断：

这是 Gradle 配置层限制，不是 DB 连接失败。很多本地 H2 或无密码测试库会使用空密码。

处理建议：

暂时用 `password.set("secret")` 绕过。是否允许空密码等待评估。

### [medium] [db-annotation-compat] 旧 `@I` 表级忽略注解没有进入新 aggregate 语义

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

复现条件：

MySQL 原库包含 `__event`、`__request`、`__saga` 等框架表，表注释包含 `@I;`。

实际结果：

新 pipeline 当前不会自动按 `@I` 跳过这些表；如果不显式 exclude 或 H2 材料不移除，aggregate 会把框架表纳入发现范围。

判断：

这是旧插件注解约定与新 pipeline source/canonical 的兼容差异。

处理建议：

当前 H2 材料直接跳过 `__*` 表。是否支持 `@I` 等待评估。

### [medium] [db-annotation-compat] 旧 `@Spe` / `@Fac` 语义没有进入新 aggregate planner

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

实际结果：

H2 dogfood plan 中 28 张表都规划了 `aggregate/factory.kt.peb` 和 `aggregate/specification.kt.peb`。

判断：

旧插件里 `@Fac`、`@Spe` 影响 factory/specification 生成行为；新 pipeline 当前不消费这两个注解。

处理建议：

当前 H2 材料剥离 `@Spe` / `@Fac`，先验证核心 aggregate 生成。是否继承旧语义等待评估。

### [medium] [db-annotation-compat] `@T=UserMessageExtend?` 的旧 nullable 写法需要决策

复现命令：

```powershell
mysql --protocol=TCP --host=127.0.0.1 --port=3306 --user=root --password=123456 --database=only_danmuku --execute="SELECT TABLE_NAME, COLUMN_NAME, COLUMN_COMMENT FROM information_schema.columns WHERE table_schema='only_danmuku' AND COLUMN_COMMENT REGEXP '@(T|Type)=[^;]*\\\\?' ORDER BY TABLE_NAME, ORDINAL_POSITION;"
```

实际结果：

```text
customer_message.extend_json  扩展信息@T=UserMessageExtend?
```

判断：

旧体系允许在 `@T` 里带 `?`。新 pipeline 的 type registry key 是 simple name，`UserMessageExtend?` 不会匹配 `UserMessageExtend`。如果不处理，后续可能生成错误类型。

处理建议：

当前 H2 材料把 `@T=UserMessageExtend?` 规范化为 `@T=UserMessageExtend`，nullable 由列本身 `NULL` 表达。是否兼容旧写法等待评估。

### [medium] [design-source-compat] 旧 design tag `payload` / `de` 不会被新 generator 消费

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

实际结果：

当前 active design 输入有：

```text
payload: 1
de: 1
```

但 plan 中没有 `design-api-payload` / `design-domain-event` 产物。

判断：

新 canonical 当前识别 `api_payload` 和 `domain_event`，不是旧 tag `payload` 和 `de`。

处理建议：

这是兼容性决策点。如果新项目输入会统一改新 tag，可以不修旧 tag 兼容。

### [low] [layout-default] `designQueryHandler` 默认输出路径和 only-danmuku 旧布局不同

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

实际结果：

新 plan 默认输出：

```text
only-danmuku-adapter/src/main/kotlin/edu/only4/danmuku/adapter/queries/authorize/AutoLoginQryHandler.kt
```

旧项目已有文件位置：

```text
only-danmuku-adapter/src/main/kotlin/edu/only4/danmuku/adapter/application/queries/authorize/AutoLoginQryHandler.kt
```

判断：

这是默认 layout 与真实项目旧布局差异，不一定是框架 bug，因为新 pipeline 已有 `layout.designQueryHandler` 配置点。

处理建议：

dogfood 如需贴近旧项目，可在 `build.gradle.kts` 显式配置 layout；默认值是否调整等待评估。

### [blocker] [design-query-list-compat] `SKIP` 迁移时新 query-list handler 与旧 query-list request 契约不兼容

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kGenerate
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache :only-danmuku-domain:compileKotlin :only-danmuku-adapter:compileKotlin
```

复现条件：

- `generators.designQuery` 与 `generators.designQueryHandler` 同时启用。
- `conflictPolicy` 保持默认 `SKIP`。
- `only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/queries/statistics/GetSearchKeywordTopListQry.kt` 已由旧插件生成并存在。
- 新 pipeline 默认 `designQueryHandler` 输出到 `adapter/queries/...`，没有命中旧项目已有的 `adapter/application/queries/...` 文件，因此生成了一个新 handler。

实际结果：

`cap4kGenerate` 生成：

```text
only-danmuku-adapter/src/main/kotlin/edu/only4/danmuku/adapter/queries/statistics/GetSearchKeywordTopListQryHandler.kt
```

随后 `:only-danmuku-adapter:compileKotlin` 失败：

```text
Unresolved reference 'Response'
Return type of 'exec' is not a subtype of the return type of the overridden member
```

旧项目中 `GetSearchKeywordTopListQry` 的契约是：

```kotlin
class Request : ListQueryParam<Item>
data class Item(...)
```

新 pipeline 生成的 handler 假设契约是：

```kotlin
ListQuery<GetSearchKeywordTopListQry.Request, GetSearchKeywordTopListQry.Response>
```

判断：

这不是单纯模板空行或 import 问题，而是 in-place 迁移时的契约差异被 `SKIP` 放大：query 本体因为已存在被跳过，handler 因默认 layout 不同被新生成，最终新 handler 编译到旧 query 契约上。

处理建议：

需要决策迁移策略。可选方向包括：要求迁移时覆盖旧 query/list/page 族；为 only-danmuku 这类旧项目显式配置 query handler layout，使 handler 也被 `SKIP`；或者新增旧契约兼容模板。是否为新 pipeline 保留旧 `Item` 契约兼容等待评估。

### [blocker] [aggregate-boundary] 新 aggregate 生成没有正确区分聚合根和聚合内实体

状态：

已在 cap4k `bb6f83a1 fix aggregate root artifact boundaries` 修复，并发布 `0.5.0-SNAPSHOT` 后在 `only-danmuku` 复验。

复验命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache --refresh-dependencies cap4kGenerate
.\gradlew.bat --no-configuration-cache --no-build-cache :only-danmuku-domain:compileKotlin
```

复验结果：

非聚合根 repository 不再生成；子实体和子实体 S 类已归入聚合根包组；domain 编译通过。

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kGenerate
```

复现条件：

H2 schema 中存在旧注解 `@P` 表达父实体/父聚合，例如：

```sql
COMMENT ON TABLE `customer_video_series_video` IS '用户视频序列视频关联;@P=customer_video_series';
COMMENT ON TABLE `video_file_variant` IS '视频文件分辨率档位;@P=video_file';
```

实际结果：

新 pipeline 生成了非聚合根 repository，例如：

```text
only-danmuku-adapter/src/main/kotlin/edu/only4/danmuku/adapter/domain/repositories/CustomerVideoSeriesVideoRepository.kt
```

同时 entity/schema 也按自身表名作为独立聚合包生成，例如：

```text
domain/aggregates/customer_video_series_video/CustomerVideoSeriesVideo.kt
domain/_share/meta/customer_video_series_video/SCustomerVideoSeriesVideo.kt
```

但旧体系和 DDD 语义应为：

```text
domain/aggregates/customer_video_series/CustomerVideoSeriesVideo.kt
domain/_share/meta/customer_video_series/SCustomerVideoSeriesVideo.kt
```

且只有 `customer_video_series` 聚合根应该有 repository。

判断：

这不是 DB 材料缺信息。`@P` 已经能区分聚合根与聚合内实体，新 pipeline source/canonical 模型也已有 `parentTable`、`aggregateRoot`、`parentEntityName` 字段。问题在后续 canonical 装配和 aggregate planners 没有一致使用聚合边界：repository/factory/specification/wrapper 等根级产物不应为非 root 实体生成；entity/schema/local enum 等实体级产物也应路由到所属聚合根包组，而不是自身表名包组。

处理建议：

先在 cap4k 侧修 aggregate canonical/package routing：计算每张表的所属 aggregate root table，非 root 实体沿父链归入 root 包组；`repositories` 只来自 `aggregateRoot=true` 的实体；root-only artifact planner 只处理 root；schema/entity 等实体级 planner 保留生成但使用 root package group。

### [info] [h2-material] H2 约束名需要全局唯一，转换材料已加表名前缀

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan
```

实际结果：

原始 MySQL dump 中多个表复用 `uk_v_email`、`uk_i` 等唯一键名。H2 执行 schema 时失败：

```text
Constraint "uk_v_email" already exists
```

判断：

这是 H2 与 MySQL 的方言差异，不是当前 pipeline 生成逻辑问题。

处理建议：

H2 转换脚本已把唯一约束名改成 `{table}_{constraint}`，仅用于 dogfood 测试材料。

### [medium] [design-source-normalization] 完整 design 输入中 7 个旧 entry 超出当前 nested model 能力

复现命令：

```powershell
.\docs\dogfood\normalize-design-input.ps1
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kPlan
```

实际结果：

标准化脚本把以下 7 个 entry 写入 `codegen/design/skipped-design.json`，没有交给 pipeline 生成：

```text
api_payload admin_category.getCategoryTree
api_payload category.getCategoryTree
command video_post.SyncVideoPostProcessStatus
query category.GetCategoryTree
query message.GetNoReadMessageCountGroup
query video_comment.VideoCommentPage
api_payload account.batchSaveAccountList
```

判断：

这些 entry 主要使用旧模板能力：多层嵌套字段、递归 tree response、`Item` 响应模型。当前 pipeline design renderer 只支持一层 nested field，并固定生成 Request/Response 模型，所以直接进入 plan 会失败。

处理建议：

短期 dogfood 先跳过并记录，避免阻塞其余 854 个可生成产物验证。后续如果要追平旧插件，需要单独设计 nested model 能力，而不是在当前标准化脚本里静默降级生成错误结构。
