[English](README.md) | **简体中文** | [Deutsch](README.de.md) | [Español](README.es.md) | [Français](README.fr.md) | [日本語](README.ja.md) | [한국어](README.ko.md)

# Proxmox-VE-Android

> 本项目源自上游 [5p00kyy/Proxmox-VE-Mobile](https://github.com/5p00kyy/Proxmox-VE-Mobile)，以独立新项目重新建库，不再是该上游的 fork。在上游代码基础上，本项目包含：10 种语言翻译（英语、德语、西班牙语、简体中文、法语、意大利语、葡萄牙语、俄语、日语、韩语）及登录页语言切换；界面打磨，包括列表卡片折叠、紧凑的系统状态卡、横排快捷操作和更规整的虚拟机/容器详情操作；轻量 WebView 访客控制台，LXC 用 xterm.js、虚拟机用 noVNC；真正可用的设置，包括深浅色主题、刷新间隔、自动登录和反馈问题入口；经正式签名的 1.0.0 正式版，通过 GitHub Releases 分发，今后推送版本标签时自动发布新版；以及应用更名为 Proxmox VE Android。

`Proxmox VE Android` 是一款 Android 客户端，可在手机或平板上浏览和操作 Proxmox VE 环境。

当前 beta 版本聚焦于常见的移动端运维流程：登录、安全连接处理、仪表盘可见性、节点导航、VM/LXC 生命周期操作、任务跟进，以及只读的基础设施查看。这是一个单模块的 Kotlin/Jetpack Compose 应用，正在从“界面自带 Retrofit 调用”向“以仓库（repository）为支撑的功能切片”演进。本 README 描述的是仓库当前实际状态，而非最终目标形态。

## 1.0.0 更新内容

与首个 Beta 版相比，1.0.0 是第一个正式版：[1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0)。

- **首个正式版。** 版本 1.0.0（versionCode 4），通过 GitHub Releases 分发，使用项目正式签名。今后推送版本标签（`v*`）时会自动发布新版。
- **虚拟机/容器控制台。** 轻量 WebView 控制台：LXC 用 xterm.js、虚拟机用 noVNC，直接加载 Proxmox 自带的控制台页面；密码登录会话用会话 ticket Cookie 认证。
- **真正可用的设置。** 深色/浅色主题、仪表盘/虚拟机/容器/任务列表 10–60 秒刷新间隔、已保存账号自动登录，以及跳转到 GitHub Issues 的反馈问题入口。生物识别和通知开关背后没有任何实现、只是占位，已删除。
- **本地化。** 应用已翻译为 10 种语言：英语、德语、西班牙语、简体中文、法语、意大利语、葡萄牙语、俄语、日语、韩语。本 README 提供 7 种语言版本。
- **界面打磨。** 列表卡片可折叠、系统状态卡更紧凑（CPU、已用/总内存、运行时间）、快捷操作横排、登录页可切换语言、虚拟机/容器详情操作更规整。
- **登录与 TLS。** 主机/IP 输入自动规范为 HTTPS、自签名证书首次使用信任流程并以 SHA-256 指纹固定、支持密码或 API 令牌登录。
- **新名称。** 由 Proxmox VE Mobile 更名为 Proxmox VE Android：应用名、设置页顶部与关于对话框同步改名。

## 正式版

当前正式版是 [1.0.0](https://github.com/iso-lib/Proxmox-VE-Android/releases/tag/v1.0.0)（versionCode 4）。与首个 Beta 版相比的改动见上文 [1.0.0 更新内容](#100-更新内容)。

分发方式：

- GitHub Releases 是下载渠道，每个 release 都附带已签名的 APK。
- 今后推送版本标签（`v*`）时会自动发布新版。
- Play Store 和 F-Droid 的打包暂缓。

支持的环境目标：

- Android API 24 或更高版本。
- Proxmox VE 8.x 是主要的冒烟测试目标。
- 独立节点和小型集群在范围内。
- Proxmox Backup Server 的管理功能不在范围内。

### 安装 APK

1. 打开 `1.0.0` 对应的 GitHub Release。
2. 下载附加的 `proxmox-ve-android-1.0.0.apk`。
3. 确认 release 说明中注明该 APK 为已签名的正式版构建。
4. 在 Android 上，允许你用来下载 APK 的浏览器或文件管理器安装应用。
5. 打开 APK 并按 Android 的安装提示操作。

只安装本仓库 GitHub Releases 中附加的 APK。Debug APK 和未签名的 workflow 产物仅用于项目验证，不应被视为公开下载。

## 截图

截图和短录屏对 `v0.1.0-beta.1` 很有用，但如果没有公开安全的截图素材，就应当省略。合适的截图包括：

- 登录界面，可见 TLS/指纹控件，使用无敏感信息的示例主机文本。
- 仪表盘，仅使用通用或一次性的实验资源。
- VM 列表/详情和 LXC 列表/详情，使用一次性的客户机名称。
- 任务详情/日志，其中 UPID、用户名、节点名、IP 以及敏感日志值已打码，或取自一次性实验环境。
- 一个只读的管理切片，例如存储、网络、用户、备份或集群，使用经过脱敏的示例数据。

不要发布会泄露以下信息的截图：私有主机名、公网或内网 IP 地址、用户名、备份备注、带有环境细节的任务日志、API 令牌、ticket、Cookie、与私有服务器绑定的指纹，或桌面/用户专属路径。如果没有经过脱敏的真实截图，请使用一次性的 Proxmox 实验环境，或者先省略截图，直到能拿到公开安全的素材。

安全的示例包括：占位主机文本、通用的节点名如 `pve-demo`、一次性的客户机名如 `demo-vm-101`、已打码的 UPID，以及来自一次性实验环境的存储或集群视图。

在向 GitHub Release 附加截图或录屏之前，请在 release 说明或 [`docs/beta-smoke-qa.md`](docs/beta-smoke-qa.md) 中记录一份公开媒体清单。清单应列出每个文件名、对应界面或流程、来源类型、使用过的脱敏标识、说明或替代文本，以及 QA 状态。合适的候选文件名有 `login-tls.png`、`dashboard.png`、`vm-detail.png`、`lxc-detail.png`、`task-detail.png` 和 `read-only-admin.png`。

## 本地化

本应用通过以下语言资源发布字符串：

- 默认（`values`）：英语
- `values-de`：德语
- `values-es`：西班牙语
- `values-zh-rCN`：简体中文
- `values-fr`：法语
- `values-it`：意大利语
- `values-pt`：葡萄牙语
- `values-ru`：俄语
- `values-ja`：日语
- `values-ko`：韩语

本地化基于 Android 的 `strings.xml` 文件实现。新增语言时应保留字符串名、占位符、格式说明符和转义方式，使翻译与默认资源在行为上完全一致。

## 当前状态

从代码检查来看，已实现的功能包括：

- 针对用户提供的 Proxmox 主机和端口的手动登录。
- 针对用户提供的 Proxmox 主机和端口的密码登录或 API 令牌登录。
- 可选的加密凭据存储，下次启动时自动填充。
- 可选的 SHA-256 证书指纹固定，用于自签名 HTTPS 服务器。
- 仪表盘节点列表、基本状态展示、任务活动摘要、轮询和手动刷新，均通过仓库支撑的功能 ViewModel 实现。
- 节点详情状态加载，通过仓库支撑的功能 ViewModel 实现，可从仪表盘节点卡片进入，且节点身份得以保留。
- VM 列表/详情加载，以及启动、优雅关机、强制停止、重启、删除、返回的任务通知、直达任务详情的链接、按 VMID 过滤的任务历史，和只读的配置/快照查看，均通过仓库支撑的功能 ViewModel 实现。
- 容器列表加载，以及启动、优雅关机、强制停止、重启、删除、返回的任务通知、直达任务详情的链接，和按 VMID 过滤的任务历史，均通过仓库支撑的功能 ViewModel 实现。
- 容器详情状态和快照查看，通过仓库支撑的功能 ViewModel 实现，可从 LXC 列表进入，且节点身份得以保留。
- 任务历史加载，通过仓库支撑的功能 ViewModel 实现，支持节点选择、状态/类型/VMID 过滤、详情/状态查看、任务日志查看，以及运行中任务的停止请求。
- 存储列表和只读的存储内容浏览，通过仓库支撑的功能 ViewModel 实现，具备加载、错误、刷新和空状态。
- 网络接口列表，通过仓库支撑的功能 ViewModel 实现，具备加载、错误、重试、刷新和已缓存节点选择。
- 用户列表，通过仓库支撑的功能 ViewModel 实现，具备加载、错误、重试、刷新和空状态。
- 备份列表，通过仓库支撑的功能 ViewModel 实现，具备节点选择、存储过滤、部分存储警告、重试、刷新和空状态。
- 集群状态，通过仓库支撑的只读界面实现，展示仲裁（quorum）、投票和节点成员状态。

明确尚未完成或仅部分实现的内容：

- 部分界面仍是占位界面或只读界面，计划中的流程正在有意识地逐步建设。
- 部分设置目前只是本地界面状态，既未持久化，也未接入运行时行为。
- 高级操作，如节点重启/关机/终端、备份修改、用户修改、VM 控制台/配置编辑，以及容器详情资源/控制台操作，均不在第一个 beta 的范围内。
- 超出 beta 范围的备份、用户、设置和 LXC 详情控件处于禁用状态，或标注为只读，而不是被呈现为可用功能。
- 显式的不安全 TLS 回退仅用于 debug 版本；自签名服务器应优先使用 SHA-256 证书指纹固定。
- 单元测试覆盖已涵盖会话/认证、仪表盘、节点、VM、LXC、任务、网络、存储、用户、备份、安全和本地化衔接点，但更广泛的单元、插桩和截图测试覆盖仍缺失。

## 已知限制

以下限制对 `v0.1.0-beta.1` 而言是预期的，应在 release 说明中体现：

- 客户机控制台访问不在第一个 beta 的范围内。
- VM 和 LXC 的配置编辑不在第一个 beta 的范围内。
- 快照创建、删除和回滚不在第一个 beta 的范围内。
- 备份创建、恢复、下载和删除不在第一个 beta 的范围内。
- 用户创建、编辑和删除流程不在第一个 beta 的范围内。
- 节点重启、关机、终端和高级服务操作不在第一个 beta 的范围内。
- 存储、网络、用户、备份和集群区域主要为只读。
- 面向密码登录的 TFA 专门处理尚未完成；在适用情况下，API 令牌登录是推荐的 beta 替代方案。
- 在打 beta 标签之前必须完成首个 beta 的冒烟 QA；更广泛的验证矩阵将在第一个公开 beta 之后继续进行。

## 技术栈

- Kotlin
- Jetpack Compose
- Material 3
- AndroidX Navigation Compose
- Retrofit with Gson
- OkHttp
- Kotlin Coroutines
- `EncryptedSharedPreferences`，用于保存凭据

## 架构

该项目目前是轻量级结构，而非严格分层：

- `presentation/`：Compose 界面、导航和 `MainViewModel`
- `data/api/`：Retrofit API 定义，以及认证/客户端配置
- `data/model/`：API 模型
- `data/security/`：加密凭据存储及凭据存储契约
- `data/session/`：当前认证会话的所有权和会话服务契约
- `presentation/auth/`：认证界面状态/会话控制器
- `data/dashboard/`、`data/node/`、`data/vm/`、`data/lxc/`、`data/task/`、`data/network/`、`data/storage/`、`data/user/`、`data/backup/`、`data/cluster/`：首批以仓库为支撑的垂直功能切片

本应用正在向以仓库为支撑的功能切片演进。仪表盘、节点详情、VM 列表/详情、LXC 列表/详情、任务、网络、存储、用户、备份和集群状态现在都使用专用的仓库和功能 ViewModel。VM 详情现在通过仓库路径包含只读的配置和快照数据。认证状态/API 服务访问现在由 `AuthSessionController` 统一管理，已保存的登录数据由 `CredentialStore` 统一管理，`MainViewModel` 仍负责已缓存节点和全局对话框相关事宜。节点详情在打开 VM、LXC、存储、网络和任务视图时，现在会保留节点身份。

## 仓库结构

```text
.
|-- app/
|   |-- build.gradle.kts
|   `-- src/main/
|       |-- AndroidManifest.xml
|       |-- java/com/proxmoxmobile/
|       |   |-- data/
|       |   `-- presentation/
|       `-- res/
|-- gradle/
|   |-- libs.versions.toml
|   `-- wrapper/
|-- build.gradle.kts
|-- gradle.properties
`-- settings.gradle.kts
```

## 配置

前置条件：

- JDK 17
- Android Studio 或本地 Android SDK 安装

本地配置：

```bash
./gradlew tasks
```

如果要在本地构建，请自行创建 `local.properties`，或通过 Android Studio 配置 Android SDK。该文件有意不纳入版本控制。

## 构建与验证

在真实的 Android 环境中，常用命令如下：

```bash
./gradlew assembleDebug
./gradlew assembleRelease
./gradlew test
./gradlew lint
./gradlew compileDebugAndroidTestKotlin
```

对于 beta 发布候选版本，请运行统一的门禁：

```bash
./scripts/beta-gate.sh v0.1.0-beta.1
```

对于打标签前的最终检查，要求在同一门禁中完成冒烟证据：

```bash
REQUIRE_BETA_QA_COMPLETE=true ./scripts/beta-gate.sh v0.1.0-beta.1
```

在 QA 进行期间，汇总手动 beta 冒烟证据：

```bash
./scripts/beta-qa-status.sh
```

在打标签之前，将同样的汇总作为严格的完成检查来运行：

```bash
./scripts/beta-qa-status.sh --require-complete
```

Android Studio/模拟器的冒烟测试位于 [`app/src/androidTest`](app/src/androidTest/README.md)，可通过以下命令运行：

```bash
./gradlew connectedDebugAndroidTest
```

这些测试使用占位数据，不连接 Proxmox 主机。它们可能会重装 debug APK 或清除已保存的测试凭据，因此请在手动登录冒烟会话之前或之后运行，而不是在会话进行期间运行。

2026-06-06 的审计已在本地使用 JDK 17 和通过环境变量配置的 Android SDK 验证了 `test`、`lint` 和 `assembleDebug`。单元覆盖目前从会话/认证、节点、VM、LXC、任务和本地化衔接点开始；更广泛的行为覆盖仍需补充。

## 项目流程

- 路线图和恢复计划：[`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)
- Beta 发布计划：[`docs/beta-release-plan.md`](docs/beta-release-plan.md)
- 当前审计和功能状态：[`docs/project-audit-2026-06-06.md`](docs/project-audit-2026-06-06.md)
- 贡献者指南：[`CONTRIBUTING.md`](CONTRIBUTING.md)
- 安全策略：[`SECURITY.md`](SECURITY.md)
- 更新日志：[`CHANGELOG.md`](CHANGELOG.md)

分支策略：

- `main` 是公开 beta 标签和可发布热修复的发布分支。
- `dev` 是下一个 beta 周期的集成分支。
- 功能和修复分支应保持短生命周期，除非是发布热修复，否则目标分支应为 `dev`。
- 公开 beta 标签在 `dev` 提升到 `main` 且 beta 门禁通过后，从 `main` 切出。

Pull request 应运行 `./gradlew test`、`./gradlew lint`、`./gradlew assembleDebug` 和 `./gradlew assembleRelease`。`.github/workflows/android.yml` 中的 GitHub Actions 工作流对面向 `main` 和 `dev` 的 pull request 及推送强制执行同样的基线。

## 安全说明

- 密码凭据和 API 令牌片段可存储在加密的 shared preferences 中。
- 默认情况下，HTTPS 使用平台 TLS 验证。
- 自签名 HTTPS 服务器可以使用 Android 信任的导入 CA，或固定的 SHA-256 证书指纹。
- 只有在 debug 版本中，才能为受信任的实验服务器禁用 SSL 验证。Release 版本要求平台 TLS 验证，或配置 SHA-256 证书指纹。
- Release 版本不再请求应用范围的明文流量。Debug 版本仍允许 HTTP 以便本地测试。
- 推荐的 `openssl` 命令和首次信任策略说明，见 [`SECURITY.md`](SECURITY.md)。

## 隐私说明

- 本应用不包含遥测或分析功能。
- 在启用凭据保存时，已保存的主机详情、用户名、密码、API 令牌 ID 和 API 令牌密钥通过加密的 shared preferences 存储在本地。
- 本应用不会有意将凭据发送到配置的 Proxmox VE 端点之外的任何地方。
- 缺陷报告应删除密码、API 令牌、ticket、Cookie、CSRF 令牌、私钥、不应共享的公网 IP 细节和敏感主机名。

## 已知缺口

- 自动化测试覆盖较窄，目前集中在会话/认证、仪表盘、节点、VM、LXC、任务、网络、存储、用户、备份、安全和本地化衔接点。
- CI 已覆盖构建、lint 和单元测试。
- 若干依赖和部分界面表明存在被放弃或未完成的功能工作。
- 部分设置在运行时行为实现之前，仍处于计划中或禁用状态。

## 建议的后续步骤

1. 在真实的 Android SDK/模拟器上验证本应用，并记录哪些界面/操作在面对 Proxmox 实例时真正可用。
2. 对照真实的 Proxmox 设备和反向代理，验证自签名证书指南。
3. 执行 `v0.1.0-beta.1` 冒烟矩阵，在打标签之前只修复 beta 阻塞问题。
4. 在收集到 QA 证据后，将更新日志从 `Unreleased` 提升为 `v0.1.0-beta.1`。
5. 继续把推迟的对齐工作拆分到后续 beta 发布列车中。

具体的分阶段路线图见 [`docs/revival-plan.md`](docs/revival-plan.md)。更广泛的产品、重构、QA、流程和社区开发周期见 [`docs/development-cycle-2026.md`](docs/development-cycle-2026.md)。第一个正式 beta 的检查清单见 [`docs/beta-release-plan.md`](docs/beta-release-plan.md)。
