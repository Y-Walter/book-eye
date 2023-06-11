package walter.y.bookeye.web.domain.system.version.repository

open class SystemVersionRepositoryException(
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException() {
    class NotFound(message: String? = null, cause: Throwable? = null) : SystemVersionRepositoryException(message, cause)
}
