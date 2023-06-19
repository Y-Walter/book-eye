package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client

import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.interfaceAdapter.gateway.annotation.Client
import walter.y.bookeye.web.interfaceAdapter.gateway.env.config.GatewayEnvConfig

@Client
class SystemSettingClientImpl(
    private val gatewayEnvConfig: GatewayEnvConfig
) : SystemSettingClient {
    override fun authorize(accessKey: SystemAccessKey) {
        if (accessKey != gatewayEnvConfig.systemSetting.accessKey) {
            throw SystemSettingClientException.Unauthorized(message = "System access key is invalid.")
        }
    }
}
