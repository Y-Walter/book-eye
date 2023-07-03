package walter.y.bookeye.web.interfaceAdapter.repository.user.account

import org.springframework.security.crypto.password.PasswordEncoder
import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.account.repository.UserAccountRepository
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.interfaceAdapter.repository.annotation.Repository

@Repository
class UserAccountRepositoryImpl(
    private val passwordEncoder: PasswordEncoder
) : UserAccountRepository {
    override fun getOrNull(principalName: PrincipalName): UserAccountEntity? {
        val userAccount = try {
            UserAccountEntity(
                userId = UserId(1L),
                userAccountId = UserAccountId(1L),
                principalName = principalName,
                userAccountPassword = UserAccountPassword.Source("Account-Password-Used-4-Development-Only").let {
                    UserAccountPassword.Builder(it, passwordEncoder).build()
                },
                isEnabled = true
            )
        } catch (ex: Exception) {
            null
        }
        return userAccount
    }
}
