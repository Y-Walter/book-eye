package walter.y.bookeye.web.interfaceAdapter.api.path.controller

import org.apache.commons.logging.LogFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class RootControllerAdvice {
    private val log = LogFactory.getLog(this::class.java)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException
    ): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleIllegalArgumentException]", ex)
        return ResponseEntity(
            ApiErrorResDTO.invalidArgument(ex.message ?: "some argument is invalid"),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(
        ex: IllegalStateException
    ): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleIllegalStateException]", ex)
        return ResponseEntity(
            ApiErrorResDTO.unknown(),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknownException(ex: Exception): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleUnknownException]", ex)
        return ResponseEntity(
            ApiErrorResDTO.unknown(),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}
