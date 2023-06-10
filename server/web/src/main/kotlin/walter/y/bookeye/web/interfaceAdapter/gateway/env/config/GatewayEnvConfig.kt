package walter.y.bookeye.web.interfaceAdapter.gateway.env.config

import org.springframework.boot.context.properties.ConfigurationProperties
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import java.io.File

@ConfigurationProperties(prefix = "app.gateway.env")
class GatewayEnvConfig(
    val systemSetting: SystemSetting
) {
    val systemVersion = kotlin.run {
        val version = File("version.txt").readText()
            .trim()
        SystemVersion(version)
    }
    class SystemSetting(
        accessKey: String
    ) {
        val accessKey = SystemAccessKey(accessKey)
    }
}
