package walter.y.bookeye.web.domain.password

class PasswordStrength(
    value: Int?
) {
    val value = kotlin.run {
        requireNotNull(value) { "passwordStrength should be not null" }
        require(value in MIN..MAX) { "passwordStrength should be $MIN to $MAX" }
        value
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is PasswordStrength) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value
    }

    companion object {
        const val MIN = 4
        const val MAX = 31
    }
}
