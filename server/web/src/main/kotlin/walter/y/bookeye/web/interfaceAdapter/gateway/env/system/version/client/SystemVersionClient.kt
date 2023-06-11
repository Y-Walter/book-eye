package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client

import walter.y.bookeye.web.domain.system.version.model.SystemVersion

interface SystemVersionClient {

    /**
     * @exception SystemVersionClientException.NotFound
     * @exception SystemVersionClientException.Unknown
     */
    fun getVersion(): SystemVersion
}
