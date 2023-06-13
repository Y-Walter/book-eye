package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import org.apache.commons.logging.LogFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestControllerAdvice
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepositoryException
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO
import walter.y.bookeye.web.useCase.system.AccessKeyValidateUseCase
import walter.y.bookeye.web.useCase.system.input.AccessKeyValidateInput

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class SystemControllerAdvice(
    private val accessKeyValidateUseCase: AccessKeyValidateUseCase
) {
    private val log = LogFactory.getLog(this::class.java)

    @ModelAttribute
    fun authorize(
        @RequestHeader("x-system-key") systemKey: String?
    ) {
        log.info("[authorize]")
        val accessKey = try {
            SystemAccessKey(systemKey)
        } catch (ex: IllegalArgumentException) {
            throw ApiSystemAccessKeyInvalidException(ex)
        }
        accessKeyValidateUseCase.execute(AccessKeyValidateInput(accessKey = accessKey))
    }

    @ExceptionHandler(SystemSettingRepositoryException::class)
    fun handleSystemSettingRepositoryException(
        ex: SystemSettingRepositoryException
    ): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleSystemSettingRepositoryException]", ex)
        return when (ex) {
            is SystemSettingRepositoryException.Unauthorized ->
                ResponseEntity(
                    ApiErrorResDTO.unauthorized("System authorization is failed."),
                    HttpStatus.UNAUTHORIZED
                )
            is SystemSettingRepositoryException.NotFound -> throw IllegalStateException(ex)
            else -> throw IllegalStateException(ex)
        }
    }

    @ExceptionHandler(SystemVersionRepositoryException::class)
    fun handleSystemVersionRepositoryException(
        ex: SystemVersionRepositoryException
    ): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleSystemVersionRepositoryException]", ex)
        when (ex) {
            is SystemVersionRepositoryException.NotFound -> throw IllegalStateException(ex)
            else -> throw IllegalStateException(ex)
        }
    }

    @ExceptionHandler(ApiSystemAccessKeyInvalidException::class)
    fun handleSystemAccessKeyInvalidException(
        ex: ApiSystemAccessKeyInvalidException
    ): ResponseEntity<ApiErrorResDTO> {
        log.error("[handleSystemAccessKeyInvalidException]", ex)
        return ResponseEntity(
            ApiErrorResDTO.unauthorized("System authorization is failed."),
            HttpStatus.UNAUTHORIZED
        )
    }
}
