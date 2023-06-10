package walter.y.bookeye.web.interfaceAdapter.client.system.setting

open class SystemSettingClientException(
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException() {
    class Unauthorized(message: String? = null, cause: Throwable? = null) : SystemSettingClientException(message, cause)

    class NotFound(message: String? = null, cause: Throwable? = null) : SystemSettingClientException(message, cause)
}
