package walter.y.bookeye.web.domain.user.model

class UserId(
    value: Long?
) {
    val value = kotlin.run {
        requireNotNull(value) { "userId should not be null" }
        require(value in MIN..MAX) { "userId should be $MIN to $MAX" }
        value
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is UserId) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        private const val MIN = 1
        private const val MAX = Long.MAX_VALUE
    }
}
