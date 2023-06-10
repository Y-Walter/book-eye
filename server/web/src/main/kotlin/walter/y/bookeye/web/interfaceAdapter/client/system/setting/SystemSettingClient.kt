package walter.y.bookeye.web.interfaceAdapter.client.system.setting

import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey

interface SystemSettingClient {
    /**
     * @exception SystemSettingClientException.Unauthorized
     * @exception SystemSettingClientException.NotFound
     * @exception SystemSettingClientException.Unknown
     */
    fun authorize(accessKey: SystemAccessKey)
}
