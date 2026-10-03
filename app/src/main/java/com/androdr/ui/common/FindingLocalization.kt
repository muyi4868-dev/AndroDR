package com.androdr.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.androdr.sigma.Finding

/**
 * Presentation-only Simplified Chinese localization for SIGMA finding text.
 * Detection rules, IOC matching, severity, telemetry and persistence are untouched.
 * Unknown/new remote rules deliberately fall back to the original rule text.
 */
@Composable
fun localizedFindingTitle(finding: Finding): String {
    if (!isSimplifiedChinese()) return finding.title
    val pair = zhTitles[finding.ruleId] ?: return finding.title
    return if (finding.triggered) pair.first else pair.second
}

@Composable
fun localizedFindingDescription(finding: Finding): String {
    if (!isSimplifiedChinese()) return finding.description
    return zhDescriptions[finding.ruleId] ?: finding.description
}

@Composable
fun localizedFindingRemediation(finding: Finding): List<String> {
    if (!isSimplifiedChinese()) return finding.remediation
    return zhRemediation[finding.ruleId] ?: finding.remediation
}

@Composable
private fun isSimplifiedChinese(): Boolean {
    val locale = LocalConfiguration.current.locales[0]
    return locale.language == "zh" && (locale.country.isBlank() || locale.country.equals("CN", true) || locale.country.equals("SG", true))
}

private val zhTitles = mapOf(
    "androdr-001" to ("已知恶意应用包" to "未发现已知恶意应用包"),
    "androdr-002" to ("恶意签名证书" to "签名证书未命中已知威胁"),
    "androdr-003" to ("恶意域名连接" to "未发现恶意域名连接"),
    "androdr-004" to ("已知恶意 APK" to "APK 哈希未命中已知恶意样本"),
    "androdr-005" to ("Graphite / Paragon 间谍软件迹象" to "未发现 Graphite / Paragon 迹象"),
    "androdr-010" to ("侧载应用" to "应用来源可信"),
    "androdr-011" to ("监控类权限" to "未发现异常监控权限组合"),
    "androdr-012" to ("无障碍服务滥用" to "未发现无障碍服务滥用"),
    "androdr-013" to ("设备管理员权限滥用" to "未发现设备管理员权限滥用"),
    "androdr-014" to ("应用冒充" to "未发现应用冒充"),
    "androdr-015" to ("未识别的系统应用" to "系统应用已识别"),
    "androdr-016" to ("伪装成系统应用" to "未发现系统名称伪装"),
    "androdr-017" to ("跟踪软件特征组合" to "未发现跟踪软件特征组合"),
    "androdr-020" to ("发现间谍软件文件痕迹" to "未发现间谍软件文件痕迹"),
    "androdr-040" to ("USB 调试已开启" to "USB 调试已关闭"),
    "androdr-041" to ("开发者选项已开启" to "开发者选项已关闭"),
    "androdr-042" to ("允许安装未知来源应用" to "未知来源安装已关闭"),
    "androdr-043" to ("未设置屏幕锁" to "屏幕锁已启用"),
    "androdr-044" to ("安全补丁已过期" to "安全补丁处于较新状态"),
    "androdr-045" to ("Bootloader 已解锁" to "Bootloader 已锁定"),
    "androdr-046" to ("无线 ADB 已开启" to "无线 ADB 已关闭")
)

private val zhDescriptions = mapOf(
    "androdr-001" to "应用包名命中已知恶意软件 IOC 数据库。",
    "androdr-002" to "应用签名证书哈希命中已知威胁证书。",
    "androdr-003" to "DNS 查询命中威胁情报中的已知命令与控制（C2）域名。",
    "androdr-004" to "APK 文件 SHA-256 哈希命中已知恶意软件样本。",
    "androdr-010" to "该应用并非通过受信任的应用商店安装。",
    "androdr-011" to "侧载应用同时持有多项可用于监控的敏感权限。",
    "androdr-012" to "侧载应用注册了无障碍服务，可能具备读取屏幕和辅助输入能力。",
    "androdr-013" to "侧载应用注册了设备管理员组件，可能阻止正常卸载。",
    "androdr-014" to "应用名称或包名与知名应用相似，但安装来源不受信任。",
    "androdr-015" to "预装系统应用未命中当前已知 OEM / AOSP / Google 白名单。部分运营商或厂商预装也可能出现此情况。",
    "androdr-016" to "侧载应用使用了类似系统组件的显示名称。",
    "androdr-017" to "侧载应用同时具备无障碍服务与多项监控类权限，这是一类跟踪软件常见组合。"
)

private val zhRemediation = mapOf(
    "androdr-001" to listOf("如果确认不是误报，请立即卸载此应用。"),
    "androdr-002" to listOf("该应用由已知威胁开发者使用的证书签名。即使名称看起来正常，也建议卸载并核实来源。"),
    "androdr-003" to listOf("设备曾尝试连接已知 C2 服务器。请查看对应应用和时间线，并优先卸载来源不明的相关应用。"),
    "androdr-004" to listOf("该应用文件哈希命中已知恶意样本。建议立即前往 设置 > 应用 > 找到该应用 > 卸载。"),
    "androdr-010" to listOf("该应用不是从受信任应用商店安装的。请确认这是你主动安装且来源可靠的软件。"),
    "androdr-011" to listOf("该应用具备较强的监控能力。如果不是你主动安装或无法确认用途，建议卸载。"),
    "androdr-012" to listOf("该应用可能读取屏幕内容。先到 设置 > 无障碍 中关闭它的服务，再卸载应用。"),
    "androdr-013" to listOf("先到 设置 > 安全 > 设备管理应用 中取消它的管理员权限，然后再卸载。"),
    "androdr-014" to listOf("该应用疑似冒充知名应用且来源不可信。建议卸载，并从官方应用商店重新安装正版。"),
    "androdr-015" to listOf("该应用为预装系统应用，但不在当前已知白名单中。部分运营商或合作方预装可能属于正常情况，请结合设备品牌和应用名称核实。"),
    "androdr-016" to listOf("该应用名称类似系统组件，但安装来源不可信。除非明确知道其用途，否则建议卸载。"),
    "androdr-017" to listOf("该应用同时具备屏幕读取和监控权限。先在 设置 > 无障碍 中关闭它的服务，再卸载。"),
    "androdr-020" to listOf("发现与已知间谍软件相关的文件痕迹。先不要删除，以免破坏证据；如涉及重要账号或设备，建议寻求专业安全人员协助。"),
    "androdr-040" to listOf("前往 设置 > 开发者选项 > 关闭 USB 调试。这样可减少其他设备通过 USB 控制手机的风险。"),
    "androdr-041" to listOf("前往 设置 > 开发者选项，在顶部关闭开发者选项。若你当前不进行开发或调试，建议保持关闭。"),
    "androdr-042" to listOf("前往 设置 > 应用 > 特殊权限 > 安装未知应用，关闭不需要的授权，避免应用在你不知情时安装其他软件。"),
    "androdr-043" to listOf("前往 设置 > 锁屏，设置 PIN、密码或生物识别。未设置锁屏时，拿到手机的人可直接访问你的数据。"),
    "androdr-044" to listOf("前往 设置 > 系统/软件更新 检查系统更新，同时检查 Google Play 系统更新。当前安全补丁距今已超过约 90 天。"),
    "androdr-045" to listOf("Bootloader 已解锁，可能允许未签名系统组件运行。如果并非你主动解锁，建议联系设备厂商了解重新锁定方法。"),
    "androdr-046" to listOf("前往 设置 > 开发者选项 > 关闭无线调试。开启时，同一网络中的设备可能尝试连接你的手机。")
)
