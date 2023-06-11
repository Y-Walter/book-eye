package walter.y.bookeye.web.domain.system.version.model

class SystemVersion(
    value: String?
) {
    val value = kotlin.run {
        val regex = Regex(VERSION_PATTERN)
        requireNotNull(value) { "value should not be null." }
        require(value.isNotBlank()) { "version should not be blank." }
        require(regex.matches(value)) { "version should match pattern: $VERSION_PATTERN" }
        value
    }

    companion object {
        private const val VERSION_PATTERN = """(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(-[a-z\d]+|)$"""
    }
}
