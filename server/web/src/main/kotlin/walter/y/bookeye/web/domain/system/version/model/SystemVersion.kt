package walter.y.bookeye.web.domain.system.version.model

class SystemVersion(
    value: String?
) {
    val value = kotlin.run {
        val regex = Regex(VERSION_PATTERN)
        requireNotNull(value) { "value should not be null." }
        require(value.isNotBlank()) { "version should not be blank." }
        val trimmedValue = value.trim()
        require(regex.matches(trimmedValue)) { "version should match pattern: $VERSION_PATTERN" }
        trimmedValue
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is SystemVersion) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        private const val VERSION_PATTERN = """(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(-[a-z\d]+|)$"""
    }
}
