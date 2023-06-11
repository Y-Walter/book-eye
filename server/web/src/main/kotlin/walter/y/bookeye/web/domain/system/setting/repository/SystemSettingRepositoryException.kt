package walter.y.bookeye.web.domain.system.setting.repository
open class SystemSettingRepositoryException(
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException() {
    class Unauthorized(message: String? = null, cause: Throwable? = null) : SystemSettingRepositoryException(message, cause)

    class NotFound(message: String? = null, cause: Throwable? = null) : SystemSettingRepositoryException(message, cause)
}
