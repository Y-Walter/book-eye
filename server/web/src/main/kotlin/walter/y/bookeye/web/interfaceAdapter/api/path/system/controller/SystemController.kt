package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import walter.y.bookeye.web.interfaceAdapter.api.path.system.model.HealthCheckResDTO
import walter.y.bookeye.web.interfaceAdapter.api.path.system.model.VersionResDTO
import walter.y.bookeye.web.useCase.system.GetVersionUseCase
import walter.y.bookeye.web.useCase.system.input.GetVersionInput

@RestController
@RequestMapping("/api/system")
class SystemController(
    private val getVersionUseCase: GetVersionUseCase
) {
    @GetMapping("/health")
    fun checkHealth(): ResponseEntity<HealthCheckResDTO> = ResponseEntity(
        HealthCheckResDTO.success(),
        HttpStatus.OK
    )

    @GetMapping("/version")
    fun getVersion(): ResponseEntity<VersionResDTO> {
        val output = getVersionUseCase.execute(GetVersionInput())
        return ResponseEntity(
            VersionResDTO(version = output.version.value),
            HttpStatus.OK
        )
    }
}
