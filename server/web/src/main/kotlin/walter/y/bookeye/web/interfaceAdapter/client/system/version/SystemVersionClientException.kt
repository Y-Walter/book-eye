package walter.y.bookeye.web.interfaceAdapter.client.system.version

open class SystemVersionClientException(
    override val message: String?,
    override val cause: Throwable?
) : RuntimeException() {
    class NotFound(message: String? = null, cause: Throwable? = null) : SystemVersionClientException(message, cause)
}
