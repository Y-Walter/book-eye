package walter.y.bookeye.web.interfaceAdapter.api.path.model

data class ApiErrorResDTO(
    val errorCode: ErrorCode,
    val messages: List<String>
) {
    constructor(
        errorCode: ErrorCode,
        message: String
    ) : this(
        errorCode = errorCode,
        messages = listOf(message)
    )

    companion object {
        fun invalidArgument(message: String): ApiErrorResDTO = ApiErrorResDTO(
            errorCode = ErrorCode.INVALID_ARGUMENT,
            message = message
        )

        fun unauthorized(message: String): ApiErrorResDTO = ApiErrorResDTO(
            errorCode = ErrorCode.UNAUTHORIZED,
            message = message
        )

        fun notFound(message: String): ApiErrorResDTO = ApiErrorResDTO(
            errorCode = ErrorCode.NOT_FOUND,
            message = message
        )

        fun unknown(): ApiErrorResDTO = ApiErrorResDTO(
            errorCode = ErrorCode.UNKNOWN,
            message = "Unknown error happened."
        )
    }

    enum class ErrorCode {
        INVALID_ARGUMENT,
        UNAUTHORIZED,
        NOT_FOUND,
        UNKNOWN
    }
}
