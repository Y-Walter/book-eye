package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

class ApiSystemAccessKeyInvalidException(
    override val cause: Throwable?
) : RuntimeException(cause)
