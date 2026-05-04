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

### [blocker] [aggregate-inverse-navigation-owner] 父子双向关联把同一外键列同时生成成 owner，Hibernate 启动时出现 duplicated mapping

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache :only-danmuku-start:test --tests "*EngineAuditSmokeTest*"
```

复现条件：

放开真实 generated aggregate entity 扫描后，让 Hibernate/JPA 启动并装配 `video_file_post` / `video_file_post_variant`、`video_file` / `video_file_variant` 等父子实体。

实际结果：

Hibernate 启动失败。典型错误：

```text
Column 'file_post_id' is duplicated in mapping for entity 'edu.only4.danmuku.domain.aggregates.video_post.VideoFilePostVariant'
```

当前生成实体形态例如：

```kotlin
// parent
@OneToMany(...)
@JoinColumn(name = "file_post_id", nullable = false)
val variants: MutableList<VideoFilePostVariant> = mutableListOf()

// child
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "file_post_id", nullable = false)
lateinit var filePost: VideoFilePost
```

判断：

这不是单纯的 eager/lazy 选择问题，而是父子双向关联的 owner 归属生成错了。父侧 `@OneToMany` 和子侧 `@ManyToOne` 同时把同一条 FK 列声明成写入侧，导致 Hibernate 认为同一列被重复映射。当前更接近“反向导航/双向关联 owner-inverse side 合同缺失”，而不是 runtime audit 或 smoke test 本身的问题。

处理建议：

后续应沿 cap4k aggregate inverse-navigation 线修复 owner 归属合同。优先方向是：子侧 `@ManyToOne + @JoinColumn` 作为 owner，父侧 `@OneToMany(mappedBy = "...")` 作为 inverse side；如果当前框架阶段不准备稳定支持父侧反向导航，也可以先不生成父侧 collection，至少不要让两侧同时占有同一 FK 列。

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

### [resolved] [design-source-normalization] 完整 design 输入中 7 个旧 entry 超出当前 nested model 能力

状态：

cap4k nested model feature worktree 已支持多层 nested payload、递归 `self` 类型、以及由直接字段子字段定义的局部 `Item` 模型。zero 标准化脚本已改为保留显式容器字段类型，只校验每级容器前缀都有且仅有一个直接字段声明，并把 root response tree 字段 `children` 中的 `Response` / 无局部子字段的 `Item` 规范化为 `self`。

复验命令：

```powershell
pwsh -NoProfile -ExecutionPolicy Bypass -File .\docs\dogfood\normalize-design-input.ps1
```

复验结果：

标准化输出从 411 个 design entry / 7 个 skipped entry 变为 418 个 design entry / 0 个 skipped entry。以下原 skipped entry 已进入 `codegen/design/design.json`：

- `api_payload admin_category.getCategoryTree`
- `api_payload category.getCategoryTree`
- `command video_post.SyncVideoPostProcessStatus`
- `query category.GetCategoryTree`
- `query message.GetNoReadMessageCountGroup`
- `query video_comment.VideoCommentPage`
- `api_payload account.batchSaveAccountList`

剩余 skipped entry：无。

复现命令：

```powershell
.\docs\dogfood\normalize-design-input.ps1
.\gradlew.bat --refresh-dependencies --no-configuration-cache --no-build-cache cap4kPlan
```

历史实际结果：

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

历史判断：

这些 entry 主要使用旧模板能力：多层嵌套字段、递归 tree response、`Item` 响应模型。当前 pipeline design renderer 只支持一层 nested field，并固定生成 Request/Response 模型，所以直接进入 plan 会失败。

后续处理：

短期跳过策略已取消。后续如果出现新的 `Item` / `Response` 用法，标准化脚本仍应只接受显式支持的 `self` 或局部 `Item` 场景，并把未覆盖场景写入 skipped 清单而不是静默降级。

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

### [resolved] [skipped-query-tree] `GetCategoryTreeQryHandler` 递归树查询已迁移为直接读 `Category` 组树

状态：

已保留 `GetCategoryTreeQryHandler`，但移除对旧 Jimmer DTO `CategoryTreeNode` 的依赖，改为直接查询 `Category` 读模型，并按 `parentId` 在内存中递归组树。对应 skipped API payload 的类别树模型也已从 `Item` 命名改为 `Response` 命名。

判断：

旧实现依赖旧 Jimmer DTO 和递归 `Item` contract，不能原样迁移。当前实现不再依赖旧 DTO，能在新 `Response.CategoryItem` / `Response.Children` contract 下编译通过。

处理建议：

后续如果要由 generator 自动生成递归树查询，仍需要单独扩展 query handler 生成能力；当前 dogfood 先以手写迁移实现闭环。

### [blocker] [design-query-contract] 多个旧列表/分页查询被标准化成单条 `Response`，无法安全迁移旧实现

状态：

adapter query handler 已迁移普通 `Query`、unique/existence 查询、CLI handler，以及部分 Response 自带容器语义的查询，例如分类树、未读消息分组、视频全量列表、视频评论分页、清晰度 JSON 汇总。剩余 TODO 集中在旧语义为 `ListQuery` / `PageQuery`，但当前 `codegen/design/design.json` 生成出的 application query contract 仍是单条 `Response` 的条目。

典型文件：

- `customer_action/GetCollectionPageQryHandler.kt`
- `customer_action/GetUserActionsByVideoIdQryHandler.kt`
- `customer_focus/GetFansPageQryHandler.kt`
- `customer_focus/GetFocusPageQryHandler.kt`
- `customer_video_series/GetCustomerVideoSeriesListQryHandler.kt`
- `message/GetMessagePageQryHandler.kt`
- `statistics/GetSearchKeywordTopListQryHandler.kt`
- `statistics/GetWeekStatisticsInfoQryHandler.kt`
- `video/GetHotVideoPageQryHandler.kt`
- `video/GetRecommendVideosQryHandler.kt`
- `video/GetVideoPageQryHandler.kt`
- `video/GetVideoPlayFilesQryHandler.kt`
- `video_file/GetVideoFilesByVideoIdQryHandler.kt`

判断：

这不是 handler 搬运问题，而是 design 输入标准化问题。旧实现返回多条记录或分页结果；当前 contract 只能返回一条 `Response`，如果硬迁只能丢数据、取第一条或伪造分页，都会把业务语义写坏。

处理建议：

需要把这些 query 的 `responseFields` 调整为明确容器语义，例如 `items: List<Item>` 或 `page: PageData<Item>`，必要时补充 `pageNum/pageSize` 请求字段或 `PageRequest` trait，然后重新生成 application query / payload / handler 骨架。完成 contract 修正后，再按旧实现迁移真实 SQL/Jimmer 查询逻辑。

### [blocker] [aggregate-entity-defaults] 生成实体缺少 Kotlin 构造默认值，导致行为代码和测试构造成本过高

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kGenerate
```

实际结果：

生成实体构造函数没有默认值，例如：

```kotlin
class CustomerVideoSeries(
    id: Long,
    customerId: Long,
    seriesName: String,
    seriesDescription: String?,
    sort: Int,
    createUserId: Long?,
    createBy: String?,
    createTime: Long?,
    updateUserId: Long?,
    updateBy: String?,
    updateTime: Long?,
    deleted: Long
)
```

判断：

`cap4k` 的 `aggregate/entity.kt.peb` 已经支持 `field.defaultValue`，但当前 `EntityArtifactPlanner` 主要依赖 DB default，且对 `DEFAULT '0'` 这类被 SQL 引号包住的数字默认值处理不足。更重要的是，实体作为行为友好的 generated-source artifact，应该有稳定的 Kotlin 类型兜底默认值，否则用户很难在 behavior、factory、测试中构造实体。

处理建议：

在生成器侧修复，不在 zero 项目手动改生成实体。规则建议为：DB default 优先；无法从 DB default 归一化时，非空基础类型按 Kotlin 类型兜底，例如 `Long = 0L`、`Int = 0`、`String = ""`、`Boolean = false`；nullable 字段默认为 `null`；集合关系继续使用 `mutableListOf()`。需要补 aggregate planner / renderer / compile-functional 覆盖。

### [blocker] [migration-contract] Query/Cmd/Cli contract 必须由 design/generator 重生成

复现条件：

only-danmuku-zero 从旧项目迁移 controller、payload、query handler 时，部分旧 `Item` / list / page contract 被手动替换为当前新 `Response` 合约以通过编译。

实际结果：

手改可以让 `compileKotlin` 和 `build` 通过，但会让生成器职责边界变模糊：同一个 query contract 一部分来自 `codegen/design/design.json`，一部分来自迁移时对 application query / API payload / handler 的手动修补。

判断：

凡是 application query/client/command 的 `Request` / `Response` 结构、是否是 `items` 容器、是否是 `page` 容器，都必须由 design 输入和 generator 决定。手动修 contract 只能作为临时 unblock，不应该进入最终 dogfood 基准。

`docs/dogfood/audit-design-contracts.ps1` 已把这条规则固化成脚本：它从 `codegen/design/design.json` 推导所有 `*Cmd`、`*Qry`、`*Cli` 期望产物，再从 `build/cap4k/plan.json` 读取实际 `outputPath`，校验该路径上的 `Request` / `Response` 结构是否与 plan context 一致。

当前 drift 闭环记录见 `docs/dogfood/cap4k-pipeline-contract-drift-backlog.md`。

处理建议：

下一轮 zero dogfood 应优先修 `codegen/design/design.json` 或标准化脚本，再重新运行 `cap4kGenerate`。只有 generator 暂时不能表达的 controller 编排、MapStruct converter、translation 注解、前端兼容字段，才允许保留为手写迁移层。

### [medium] [managed-write-surface] managed/special-field 已进入 plan 契约，但还没有稳定落到用户 create/update 生成面

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kPlan cap4kGenerate
```

实际结果：

`build/cap4k/plan.json` 已能看到 `aggregateSpecialFieldResolvedPolicies[*].managedFields` 与 `writeSurface`，例如 `Category` 会把 `id`、`deleted`、`create_user_id`、`create_by`、`create_time`、`update_user_id`、`update_by`、`update_time` 识别成 managed。

但生成实体仍然保留这些字段，例如：

```kotlin
class Category(
    id: Long,
    createUserId: Long?,
    createBy: String?,
    createTime: Long?,
    updateUserId: Long?,
    updateBy: String?,
    updateTime: Long?,
    deleted: Long
)
```

当前还没有证据表明 application command / request DTO / client payload 等最终用户写入面已经稳定消费 `writeSurface.createAllowedFields` / `updateAllowedFields`。

判断：

这不是 special-field 解析失败。当前 `cap4k` 已经能解析 managed/special-field，并把 `writePolicy` / `writeSurface` 暴露到 canonical model 与 `cap4kPlan`。问题在于“用户不可写”目前主要还是契约层语义，尚未形成稳定的 generated write-surface enforcement。也就是说，系统已经知道哪些字段不该让用户写，但还没有在所有生成写入面上统一把它们裁掉或禁止。

处理建议：

后续应在 `cap4k` 侧补一条独立排期：优先让 command/request DTO、application write input、client payload 等 create/update surface 消费 `writeSurface`；实体字段是否需要额外的显式只读提示可后续再议，但不应先把 runtime 承载字段从实体里删掉。

### [medium] [aggregate-unique] 生成 Unique 家族命名无法表达业务命名，软删除字段进入类型名导致噪音

复现命令：

```powershell
.\gradlew.bat --no-configuration-cache --no-build-cache cap4kGenerate
```

实际结果：

DB 唯一约束包含软删除列时，生成类型名会包含 `Deleted`，例如：

```text
UniqueCategoryCodeDeleted
UniqueCategoryCodeDeletedQry
UniqueCategoryCodeDeletedQryHandler
```

同时 zero 迁移中还存在旧手写版本：

```text
application/validators/UniqueCategoryCode.kt
application/queries/category/UniqueCategoryCodeQry.kt
adapter/application/queries/category/UniqueCategoryCodeQryHandler.kt
```

判断：

当前生成器按照唯一约束字段拼接类型名，工程上可解释，但用户语义不够好。软删除列通常是唯一性 scope/filter，不是业务唯一键名称的一部分。迁移旧手写 `UniqueCategoryCode` 等文件也会和新生成的 `UniqueCategoryCodeDeleted` 家族形成重复心智负担。

处理建议：

优化 `aggregate unique` 生成能力后，再清理迁移进来的旧 Unique 手写文件。已确定的方向是使用 DDL 唯一键名称作为主入口，而不是 DSL 优先覆盖。支持 `uk` / `uk_v_<fragment>` / `<table>_uk` / `<table>_uk_v_<fragment>`，其中 `<table>_` 只作为 H2 等数据库物理约束名全局唯一要求下的可移植前缀，生成前归一化移除。软删除字段和乐观锁版本字段都作为 scope/control 字段，不参与 fallback 类型名和 request 字段。query、query handler、validator 三个产物必须共享同一个 resolved unique family name，最终类型名冲突必须 fail fast。

正式设计见 `cap4k/docs/superpowers/specs/2026-05-03-cap4k-aggregate-unique-family-naming-contract-design.md`。

### [medium] [design-default-value-projection] IR / drawing-board 默认值投影不能稳定保留枚举常量和空集合表达式

复现条件：

`CaptchaGenCli.Request` 迁移后需要默认值：

```kotlin
val channel: CaptchaChannel = CaptchaChannel.INLINE
val targets: List<String> = emptyList()
val templateCode: String? = null
```

实际结果：

当前 `codegen/design/design.json` 中 `CaptchaGenCli` 对应字段没有这些默认值，生成后需要手动补：

```kotlin
val channel: CaptchaChannel = CaptchaChannel.INLINE,
val targets: List<String> = emptyList(),
val templateCode: String? = null
```

判断：

design generator 的 `DefaultValueFormatter` 已支持 `emptyList()`、`null`、以及 `CaptchaChannel.INLINE` 这类常量表达式；如果用户直接在 design 输入中写这些 defaultValue，生成器应能处理。当前问题主要在分析投影 / drawing-board 输入链路：从代码分析或标准化材料重新生成时，这类默认值没有稳定保留下来。

处理建议：

补 analysis projection / drawing-board defaultValue 表达能力。第一版只需要支持稳定、可验证的常见表达式：`null`、基础类型字面量、空集合构造、枚举或常量 FQN / 类型名常量表达式。不能解析的复杂表达式应快速失败或丢入 backlog，不应静默生成缺默认值的契约。

### [low] [migration-format] 迁移进来的旧 command 文件存在 import 多余空行

复现文件：

```text
only-danmuku-application/src/main/kotlin/edu/only4/danmuku/application/commands/customer_video_series/CreateCustomerVideoSeriesCmd.kt
```

实际结果：

旧迁移文件中 wildcard import 之间存在多余空行：

```kotlin
import edu.only4.danmuku.domain.aggregates.video_quality_policy.*

import edu.only4.danmuku.domain.aggregates.video_post_processing.*

import edu.only4.danmuku.domain.aggregates.video_post.*
```

判断：

这更像旧项目 / 旧模板迁移痕迹，不是当前新 `design/command.kt.peb` 的典型输出。新模板走 `use()` / `imports()` 收集 import，正常不应为每个 import 插入空行。

处理建议：

先作为迁移清理项处理：格式化或手动清理旧迁移文件。只有在 fresh `cap4kGenerate` 后仍复现多余空行，才升级为 renderer/template bug。

### [medium] [drawing-board-json-escaping] drawing-board JSON 中泛型尖括号被写成 `\\u003c` / `\\u003e`

复现命令：

```powershell
.\gradlew.bat --no-daemon --refresh-dependencies cap4kAnalysisGenerate
```

复现文件：

```text
design/drawing_board_api_payload.json
```

实际结果：

例如 `children` 字段类型会输出成：

```json
"type": "List\u003cResponse\u003e"
```

而不是更直观的：

```json
"type": "List<Response>"
```

判断：

这不是 drawing-board 语义错误，JSON 解析后值仍然等价；根因更像 renderer 层统一 `json` filter 使用了默认 `Gson()`，开启了 HTML escaping，因此 `<` / `>` 被写成 Unicode 转义。问题主要是产物可读性和 review 体验，不是当前 analysis contract 的阻断项。

处理建议：

后续在 `cap4k` renderer 层评估是否把 Pebble `json` filter 改成 `disableHtmlEscaping()`，并补 drawing-board golden/functional 覆盖，避免只在某一类 artifact 上局部处理。

### [medium] [analysis-flow-command-handler-entity-method] analysis flow 缺少 `CommandHandlerToEntityMethod`，behavior 拆分后 handler -> entity method 链路断开

复现命令：

```powershell
.\gradlew.bat --no-daemon --refresh-dependencies cap4kAnalysisGenerate
```

复现文件：

```text
flows/*.json
only-danmuku-application/build/cap4k-code-analysis/nodes.json
only-danmuku-domain/build/cap4k-code-analysis/rels.json
```

实际结果：

- flow 产物里当前没有命中任何 `CommandHandlerToEntityMethod`
- 但 `EntityMethodToDomainEvent` 仍大量存在，例如 `anonymous.json`
- application command handler 里确实直接调用了聚合 behavior，例如：
  - `CreateCategoryCmd.Handler -> Category.changeSort / updateNodePath`
  - `PrepareVideoPostProcessingEncryptContextCmd.Handler -> VideoPostProcessing.prepareEncryptContext`

判断：

这不是 flow renderer 白名单问题，而是 analysis compiler 上游没有稳定产出这条边。当前 behavior 已从实体成员方法拆成顶层扩展函数 `*Behavior.kt`，怀疑 `CommandHandlerToEntityMethod` 的 IR 识别仍偏向成员方法/聚合类宿主调用，未完整覆盖扩展 behavior 调用。`EntityMethodToDomainEvent` 当前仍正常，因此问题主要集中在 handler -> entity method 绑定，而不是 domain-event 边整体失效。

处理建议：

后续在 `cap4k` 侧单开分析修复：针对顶层扩展 behavior 函数补 `CommandHandlerToEntityMethod` 识别回归测试，并复核是否需要同时校正 aggregate-to-entity-method / flow anonymous 聚合策略。当前先记录为排期项，不在本轮 dogfood 继续扩修。

### [low] [migration-cleanup] 工厂 Payload 中保留了不必要的业务默认值

复现文件：

```text
only-danmuku-domain/src/main/kotlin/edu/only4/danmuku/domain/aggregates/customer_action/factory/CustomerActionFactory.kt
```

实际结果：

`CustomerActionFactory.Payload` 中多个业务必填字段带默认值：

```kotlin
var customerId: UUID = UUID(0L, 0L)
var videoId: UUID = UUID(0L, 0L)
var videoOwnerId: UUID = UUID(0L, 0L)
var commentId: UUID = UUID(0L, 0L)
var actionType: ActionType = ActionType.valueOfOrNull(0) ?: ActionType.UNKNOW
```

判断：

实体 ID 字段使用 `UUID(0L, 0L)` 作为 application-side ID 的“未分配哨兵值”是框架机制，需要保留。但工厂 `Payload` 是业务输入，不是 JPA 实体状态。把业务必填字段默认成空 UUID 或 `UNKNOW` 会掩盖调用方漏传参数的问题，降低领域约束强度。

这属于 only-danmuku-zero 迁移清理项，主要来自旧模板/批量 UUID 迁移后的惯性写法，不是当前 cap4k pipeline 的阻塞缺陷。

处理建议：

暂不释放资源处理这类无关紧要的清理项。后续如果进行领域层代码卫生清理，应把工厂 `Payload` 的业务必填字段改为无默认值，允许为空的字段改为真实 nullable，例如 `commentId: UUID? = null`；实体构造函数自身的 ID 哨兵默认值仍保留。

### [known-gap] [design-validator] 复杂业务 validator 暂不能由 designValidator 表达

状态：

已将普通 validator 统一迁入 `iterate/drawing_board.json`，由 `designValidator` 生成到默认 `application.validators` 包根。复杂 validator 只保留注解 API 和 `return true` 骨架，真实业务逻辑不再通过 bootstrap slot 保留。

- `CategoryDeletionAllowed`
- `CategoryMustExist`
- `CommentDeletePermission`
- `CommentExists`
- `CommentNotClosed`
- `DanmukuDeletePermission`
- `DanmukuExists`
- `DanmukuInteractionAllowed`
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
- `VideoExists`
- `VideoIdsBelongToUser`
- `VideoInSeries`
- `VideoPostEditableStatus`
- `VideoPostExists`
- `VideoPostStatusPending`

判断：

`designValidator` 已能表达 class-level target、自定义注解参数、`String`/`Int`/`Any` 等 valueType。当前 dogfood 只要求生成可编译骨架，不要求迁移旧 validator 的业务校验逻辑。

处理建议：

bootstrap slot 不再保留 `application-package/validators`。后续如果要恢复真实 validator 逻辑，应在业务项目中手写覆盖或引入更明确的扩展机制，不能再把历史实现作为 zero dogfood 的基础输入。
