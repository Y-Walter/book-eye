package walter.y.bookeye.web.useCase.account

import org.springframework.security.crypto.password.PasswordEncoder
import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput
import walter.y.bookeye.web.useCase.annotation.UseCase

@UseCase
class GetAccountUseCaseImpl(
    private val passwordEncoder: PasswordEncoder
) : GetAccountUseCase {
    override fun execute(input: GetAccountInput): GetAccountOutput {
        val userAccount = try {
            UserAccountEntity(
                userId = UserId(1L),
                userAccountId = UserAccountId(1L),
                principalName = input.principalName,
                userAccountPassword = UserAccountPassword.Source("Account-Password-Used-4-Development-Only").let {
                    UserAccountPassword.Builder(it, passwordEncoder).build()
                },
                isEnabled = true
            )
        } catch (ex: Exception) {
            null
        }
        return GetAccountOutput(account = userAccount)
    }
}
