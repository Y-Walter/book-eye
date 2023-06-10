package walter.y.bookeye.web.interfaceAdapter.api.path.system.model

data class HealthCheckResDTO(
    val message: String
) {
    companion object {
        fun success(): HealthCheckResDTO = HealthCheckResDTO(message = "SUCCESSFUL")
    }
}
