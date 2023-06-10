package walter.y.bookeye.web.interfaceAdapter.api.path.controller

import org.apache.commons.logging.LogFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class RootControllerAdvice {
    private val log = LogFactory.getLog(this::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException
    ): ApiErrorResDTO {
        log.error("[handleIllegalArgumentException]", ex)
        return ApiErrorResDTO.invalidArgument(ex.message ?: "some argument is invalid")
    }

    @ExceptionHandler(IllegalStateException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleIllegalStateException(
        ex: IllegalStateException
    ): ApiErrorResDTO {
        log.error("[handleIllegalStateException]", ex)
        return ApiErrorResDTO.unknown()
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleUnknownException(ex: Exception): ApiErrorResDTO {
        log.error("[handleUnknownException]", ex)
        return ApiErrorResDTO.unknown()
    }
}
