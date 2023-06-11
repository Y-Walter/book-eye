package walter.y.bookeye.web.domain.system.setting.model

class SystemAccessKey(
    value: String?
) {
    private var value = kotlin.run {
        requireNotNull(value) { "systemAccessKey should not be null." }
        require(value.isNotBlank()) { "systemAccessKey should not be blank." }
        val trimmedKey = value.trim()

        require(trimmedKey.length in MIN_LENGTH..MAX_LENGTH) {
            "systemAccessKey should have $MIN_LENGTH to $MAX_LENGTH characters, but ${trimmedKey.length} characters."
        }
        val regex = Regex(PATTERN)
        require(regex.matches(trimmedKey)) { "systemAccessKey should be pattern of $PATTERN." }
        trimmedKey
    }

    fun matchesAtOnce(other: SystemAccessKey): Boolean {
        val key = value
        check(key.isNotEmpty()) { "systemAccessKey has already used more than once." }
        val matched = value == other.value
        value = ""
        return matched
    }

    companion object {
        private const val MIN_LENGTH = 24
        private const val MAX_LENGTH = 256
        private const val PATTERN = """[a-zA-Z\d_\-+*~`'"&%$@!.,]*"""
    }
}
