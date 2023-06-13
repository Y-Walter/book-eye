package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client

import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey

interface SystemSettingClient {
    /**
     * @exception SystemSettingClientException.Unauthorized
     * @exception SystemSettingClientException.NotFound
     */
    fun authorize(accessKey: SystemAccessKey)
}
