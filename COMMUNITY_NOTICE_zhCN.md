# AndroDR 简体中文社区版

本分支/构建基于上游开源项目 **AndroDR**（原作者：yasirhamza）进行简体中文本地化。

- 上游项目：`yasirhamza/AndroDR`
- 许可证：Apache License 2.0（原 `LICENSE` 保留不变）
- 本地化范围：界面、按钮、设置、风险等级、风险说明、处理建议、历史记录与时间线等显示层文字
- 未修改：扫描引擎、SIGMA 规则判断、IOC 匹配、网络检测、数据库更新与风险计算逻辑
- 专业缩写：CVE、IOC、DNS、USB、EDR、SHA-256 等按原技术名称保留
- 语言行为：简体中文系统显示中文；其他语言环境回退到上游英文

本版本为社区本地化版本，并非上游作者发布的官方中文版。

## 安装与包名

社区版使用独立 applicationId：`com.androdr.zhcn`（Debug 测试包为 `com.androdr.zhcn.debug`），因此可以与官方 AndroDR 并存，避免因签名不同导致无法覆盖安装。扫描器的“跳过自身应用”逻辑已改为读取 `BuildConfig.APPLICATION_ID`，同时保留对官方包名的排除；这只是维持原有自排除行为，不改变威胁检测规则。
