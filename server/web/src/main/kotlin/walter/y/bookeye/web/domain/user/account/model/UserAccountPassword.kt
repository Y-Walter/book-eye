package walter.y.bookeye.web.domain.user.account.model

import org.springframework.security.crypto.password.PasswordEncoder
class UserAccountPassword(
    value: String?
) {
    private var value = kotlin.run {
        requireNotNull(value) { "accountPassword should not be null" }
        val trimmedValue = value.trim()
        require(trimmedValue.isNotEmpty()) {
            "accountPassword should not be empty"
        }
        require(trimmedValue.length == LENGTH) {
            "accountPassword should be $LENGTH characters"
        }
        trimmedValue
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is UserAccountPassword) return false
        return value == other.value
    }

    fun readOnce(): String {
        check(value.isNotEmpty()) { "accountPassword is used twice" }
        val readValue = value
        value = ""
        return readValue
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        private const val LENGTH = 60
    }

    class Source(
        value: String?
    ) {
        private var value = kotlin.run {
            requireNotNull(value) { "accountPasswordSource should not be null" }
            val trimmedValue = value.trim()
            require(trimmedValue.isNotEmpty()) {
                "accountPasswordSource should not be empty"
            }
            require(trimmedValue.length in MIN_LENGTH..MAX_LENGTH) {
                "accountPasswordSource should be in $MIN_LENGTH to $MAX_LENGTH"
            }
            require(Regex(PATTERN).matches(trimmedValue)) {
                "accountPasswordSource should match `$PATTERN`"
            }
            trimmedValue
        }

        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            if (other !is Source) return false
            return value == other.value
        }

        fun readOnce(): String {
            check(value.isNotEmpty()) { "accountPasswordSource is used twice" }
            val readValue = value
            value = ""
            return readValue
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }

        companion object {
            private const val MIN_LENGTH = 8
            private const val MAX_LENGTH = 64

            /**
             * パスワード要件
             * - 英小文字1文字以上
             * - 英大文字1文字以上
             * - 数字1文字以上
             * - 記号0文字以上
             */
            private const val PATTERN = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()\-_=+\[{\]};:'",<.>/?]*$"""
        }
    }

    class Builder(
        private val source: Source,
        private val passwordEncoder: PasswordEncoder
    ) {
        fun build(): UserAccountPassword {
            val rawPassword = source.readOnce()
            val encodedPassword = passwordEncoder.encode(rawPassword)
            return UserAccountPassword(encodedPassword)
        }
    }
}
