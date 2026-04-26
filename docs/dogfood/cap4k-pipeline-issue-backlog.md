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

状态：

已在 cap4k source-db 中支持表级 `@I` / `@IGNORE`，带值写法会被拒绝。被忽略表不再进入 selected schema snapshot 和 aggregate plan。

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

已按旧注解规范补齐，无需继续通过 H2 材料绕开。

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

状态：

已按规范决策修正 cap4k 默认布局：`designQueryHandler` 默认包根改为 `adapter.application.queries`。zero dogfood 已移除显式 layout 覆盖，复验生成路径落在旧规范布局。

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

已按旧布局作为规范决策修正默认值，不再要求 dogfood 显式覆盖。

### [blocker] [design-query-list-compat] `SKIP` 迁移时新 query-list handler 与旧 query-list request 契约不兼容

状态：

默认 handler layout 已对齐旧规范布局后，这个迁移场景不会再因为 handler 路径错位而新生成一份不兼容 handler。旧 `Item` / list / page 契约兼容仍属于后续 nested model 能力决策，本轮不处理。

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

### [blocker] [design-domain-event] 旧 `de.requestFields.entity` 与新领域事件模板自动 `entity` 重复

状态：

zero 标准化脚本已在 `domain_event` 输入中丢弃 `requestFields.entity`，保留新 pipeline 的固定聚合实体语义。复验 104 个 domain event 输入中同名 `entity` request field 数量为 0，生成代码不再出现重复构造参数。

复现命令：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache compileKotlin
```

实际结果：

`:only-danmuku-domain:compileKotlin` 失败。典型错误：

```text
CategoryBasicInfoUpdatedDomainEvent.kt:18:9 Conflicting declarations:
entity: Category
entity: Category
```

生成文件示例：

```kotlin
class CategoryBasicInfoUpdatedDomainEvent(
    val entity: Category,
    val entity: Category
) {
}
```

影响范围：

当前 zero dogfood 生成的 domain event 中有 103 个文件存在重复构造参数名，主要是 `entity` 重复。

判断：

旧插件导出的 `de` 输入会显式携带：

```json
{ "name": "entity", "type": "Category", "nullable": false }
```

新 pipeline 的 `design/domain_event.kt.peb` 模板又默认给所有领域事件生成：

```kotlin
val entity: AggregateRoot
```

两条语义叠加后必然生成重复参数。这不是 Kotlin import 或格式问题，而是旧 design 输入语义与新模板默认语义没有统一。

处理建议：

需要决策领域事件 aggregate entity 的唯一来源。更稳定的方向是 canonical 层把 aggregate entity 作为领域事件固定语义，标准化旧输入时丢弃同名 `entity` request field；或者模板侧不要自动注入，完全依赖 canonical fields。不要两边同时表达。

### [blocker] [manual-boundary] zero host 缺少手写值对象 `UserMessageExtend`

状态：

已从原项目拷贝 `UserMessageExtend` 到 zero host，并补齐 `engine-json` 依赖。该类继续作为手写值对象边界，不伪装成当前 generator 产物。

复现命令：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache compileKotlin
```

实际结果：

`:only-danmuku-domain:compileKotlin` 失败。典型错误：

```text
SCustomerMessage.kt:241:80 Unresolved reference 'extend'.
```

生成代码引用：

```kotlin
Field<edu.only4.danmuku.domain.aggregates.customer_message.extend.UserMessageExtend>
```

但 zero host 中不存在：

```text
only-danmuku-domain/src/main/kotlin/edu/only4/danmuku/domain/aggregates/customer_message/extend/UserMessageExtend.kt
```

旧项目中该文件存在：

```text
only-danmuku-domain/src/main/kotlin/edu/only4/danmuku/domain/aggregates/customer_message/extend/UserMessageExtend.kt
```

判断：

这不是 aggregate/source-db 直接可生成产物，而是项目手写值对象。当前 type registry 只能告诉生成器字段类型应该引用谁，不能凭空生成该值对象实现和 converter 逻辑。

处理建议：

如果目标是 zero host 编译通过，需要把 `UserMessageExtend` 归入最小手写宿主材料，或者后续设计独立的 value-object generator。短期不建议把它伪装成 aggregate/design 产物生成。

### [blocker] [bootstrap-start] start 模块默认骨架缺少 Spring Boot dependency BOM

状态：

已在 cap4k 默认 bootstrap start module build template 中补充 `org.springframework.boot:spring-boot-dependencies:3.5.6` platform；zero host 的 start 模块也同步补齐。

复现命令：

```powershell
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache compileKotlin
```

实际结果：

`:only-danmuku-start:compileKotlin` 解析 Spring Boot starter 依赖失败：

```text
Could not find org.springframework.boot:spring-boot-starter-webflux:.
Could not find org.springframework.boot:spring-boot-starter-actuator:.
```

判断：

start 模块模板直接声明 Spring Boot starter，但没有 Spring Boot 插件或 BOM 提供版本约束。对于最小 bootstrap 骨架，依赖版本来源必须由模板自己保证。

处理建议：

保持 start module 默认骨架内置 Spring Boot BOM。后续如果引入 Spring Boot Gradle 插件，也不能让 starter 版本依赖隐式外部配置。

### [info] [dogfood-host-dependencies] zero host 需要显式提供生成代码引用的外部依赖

状态：

已补齐本轮编译所需依赖：domain 使用 `engine-json`，application 使用 `engine-common` 和 `spring-web`，adapter 使用 `engine-translation`。

判断：

这些依赖不是 generator bug。生成代码引用的 `CaptchaChannel`、`MultipartFile`、translation annotation 等类型来自真实项目的外部库或框架 API，zero host 从零生成时必须显式提供。

### [blocker] [dogfood-repository] zero host 缺少 `code-gen` Maven 仓库导致 KSP processor 无法解析

状态：

已在 zero host 的 `settings.gradle.kts` 和根 `build.gradle.kts` 补齐 AliYun `code-gen` 仓库。由于当前子模块仍声明 project-level repositories，仅配置 `dependencyResolutionManagement.repositories` 不够，根 `allprojects.repositories` 也需要包含该仓库。

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache compileKotlin
```

实际结果：

```text
Could not find com.only4:ksp-processor:0.2.0-SNAPSHOT.
```

判断：

这是 dogfood host 仓库配置问题，不是生成器产物问题。真实 `only-danmuku` 项目已有该仓库，zero host 从零复现时需要显式带上。

### [known-gap] [adapter-controller-contract] portal controller slot 已从旧 `Item` / `Converter` 契约降级为 `Response` TODO 骨架

状态：

已保留 controller 文件和 endpoint 方法签名，没有从 slot 或当前输出删除。所有历史 `*.Item`、`*.DanmukuItem`、`*.FileItem` 返回元素类型已替换为当前生成规范的 `*.Response`；历史 `*.Converter` 依赖已移除；无法由当前 generator 推导的业务编排统一改成显式 `TODO("Pending controller adapter contract implementation.")`。

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache :only-danmuku-adapter:compileKotlin
```

判断：

这不是“controller 不应该存在”，而是旧手写 controller 绑定了旧插件的 API payload `Item`/MapStruct `Converter` 运行时契约。新 pipeline 当前生成的是 `Request`/`Response` 模型，不再生成 `Converter`。因此 slot 中保留 controller 是合理的，但旧实现不能原样作为规范实现。

处理建议：

短期保持 TODO 骨架，保证完整 dogfood 编译闭环。真实业务实现后续由人工基于新 `Payload.Response` 和 application request/response 契约重写，不能再依赖旧 `Converter`。如果未来希望自动生成 controller 适配层，需要单独设计 `designController` 或 payload mapping 能力，不能把旧 MapStruct 契约重新塞回默认模板。

### [known-gap] [skipped-query-tree] `GetCategoryTreeQryHandler` 保留为递归树查询 TODO 骨架

状态：

已保留 `GetCategoryTreeQryHandler`，但移除对旧 Jimmer DTO `CategoryTreeNode` 的依赖，改为显式 TODO 骨架。对应 skipped API payload 的类别树模型也已从 `Item` 命名改为 `Response` 命名。

判断：

类别树属于当前 nested model / recursive tree 能力缺口。旧实现依赖旧 Jimmer DTO 和递归 `Item` contract，直接保留会让 zero dogfood 编译失败，也会继续暴露旧契约。

处理建议：

短期保留 TODO。后续如果要生成递归树响应，需要先扩展 nested model 能力和 query-list handler 能力，再恢复真实实现。

### [known-gap] [design-validator] 复杂业务 validator 暂不能由 designValidator 表达

状态：

已将 7 个简单字段级 `Long` validator 迁入 `iterate/drawing_board.json`，由 `designValidator` 生成到默认 `application.validators` 包根：

- `CategoryDeletionAllowed`
- `CategoryMustExist`
- `CommentExists`
- `CommentNotClosed`
- `DanmukuExists`
- `DanmukuInteractionAllowed`
- `VideoExists`

以下复杂 validator 继续保留在 bootstrap slot 的 `codegen/bootstrap-slots/application-package/validators`，同时标记为能力缺口：

- `CommentDeletePermission`
- `DanmukuDeletePermission`
- `DanmukuTextFormat`
- `MaxVideoPCount`
- `NicknameChangeAllowed`
- `NotSelf`
- `NotSelfCoin`
- `ReplyCommentExists`
- `SafeFilePath`
- `SeriesBelongToUser`
- `SeriesOwnership`
- `SeriesVideoCountLimit`
- `SufficientCoinBalance`
- `UserExists`
- `ValidateDeleteUploadSession`
- `ValidateUploadChunk`
- `ValidAuditStatus`
- `VideoCommentOwner`
- `VideoDeletePermission`
- `VideoIdsBelongToUser`
- `VideoInSeries`
- `VideoPostEditableStatus`
- `VideoPostExists`
- `VideoPostStatusPending`

判断：

当前 `designValidator` 模板只能生成字段/参数级 `Long` validator 空骨架，无法表达 class-level target、自定义注解参数、`String`/`Int?`/`Any` 等特殊 valueType，也无法保留真实业务校验逻辑。这些 validator 如果直接从 slot 删除，会导致 dogfood 完整流程后的项目缺少类型，无法保证编译闭环。

处理建议：

短期保留复杂 validator slot，作为 dogfood 编译补齐层。后续如果要继续收敛 slot，需要先扩展 `ValidatorModel` 和 `design/validator.kt.peb`，至少支持 target、valueType、annotation params，并明确生成器 conflict policy 不能覆盖用户手写逻辑。
