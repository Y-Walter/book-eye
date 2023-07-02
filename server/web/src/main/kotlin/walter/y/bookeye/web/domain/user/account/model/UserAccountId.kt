package walter.y.bookeye.web.domain.user.account.model

class UserAccountId(
    value: Long?
) {
    val value = kotlin.run {
        requireNotNull(value) { "accountId should not be null" }
        require(value in MIN..MAX) { "accountId should be $MIN to $MAX" }
        value
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is UserAccountId) return false
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
