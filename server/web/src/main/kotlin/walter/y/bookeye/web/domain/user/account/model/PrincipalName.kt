package walter.y.bookeye.web.domain.user.account.model

/**
 * RFC 7504
 * - 参考: https://qiita.com/yoshitake_1201/items/40268332cd23f67c504c
 */
class PrincipalName(
    value: String?
) {
    val value = kotlin.run {
        requireNotNull(value) { "principalName should not be null" }
        val trimmedValue = value.trim()
        require(trimmedValue.isNotEmpty()) { "principalName should not be blank" }
        require(trimmedValue.length in MIN_LENGTH..MAX_LENGTH) {
            "principalName should be $MIN_LENGTH to $MAX_LENGTH characters"
        }

        require(trimmedValue.contains(ADDRESS_SEPARATOR)) {
            "principalName should contain $ADDRESS_SEPARATOR"
        }

        val (localPart, domain) = trimmedValue.split(ADDRESS_SEPARATOR)
        require(localPart.length in LOCAL_PART_MIN_LENGTH..LOCAL_PART_MAX_LENGTH) {
            "principalName should have local-part in $LOCAL_PART_MIN_LENGTH..$LOCAL_PART_MAX_LENGTH"
        }
        require(domain.isNotEmpty()) { "principalName should have domain part" }
        require(Regex(PATTERN).matches(trimmedValue)) { "principalName should be pattern of `$PATTERN`" }
        trimmedValue
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is PrincipalName) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        private const val MIN_LENGTH = 8
        private const val MAX_LENGTH = 254
        private const val LOCAL_PART_MIN_LENGTH = 1
        private const val LOCAL_PART_MAX_LENGTH = 64
        private const val ADDRESS_SEPARATOR = "@"
        private const val LOCAL_PART_PATTERN = """(?i)[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"""
        private const val DOMAIN_PATTERN = """(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"""
        private const val PATTERN = """^$LOCAL_PART_PATTERN$ADDRESS_SEPARATOR$DOMAIN_PATTERN$"""
    }
}
