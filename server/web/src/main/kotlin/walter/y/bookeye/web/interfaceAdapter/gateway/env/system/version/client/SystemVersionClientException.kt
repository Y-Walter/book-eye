package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client

open class SystemVersionClientException(
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException() {
    class NotFound(message: String? = null, cause: Throwable? = null) : SystemVersionClientException(message, cause)
}
