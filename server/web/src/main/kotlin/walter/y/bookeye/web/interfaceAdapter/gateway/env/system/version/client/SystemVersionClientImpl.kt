package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client

import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import walter.y.bookeye.web.interfaceAdapter.gateway.annotation.Client
import walter.y.bookeye.web.interfaceAdapter.gateway.env.config.GatewayEnvConfig

@Client
class SystemVersionClientImpl(
    private val gatewayEnvConfig: GatewayEnvConfig
) : SystemVersionClient {
    override fun getVersion(): SystemVersion {
        return gatewayEnvConfig.systemVersion
    }
}
