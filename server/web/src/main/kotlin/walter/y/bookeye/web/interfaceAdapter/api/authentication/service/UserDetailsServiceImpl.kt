package walter.y.bookeye.web.interfaceAdapter.api.authentication.service

import org.springframework.security.core.userdetails.UserDetailsService
import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.interfaceAdapter.api.authentication.model.UserPrincipal
import walter.y.bookeye.web.useCase.account.GetAccountUseCase
import walter.y.bookeye.web.useCase.account.input.GetAccountInput

// @Service
class UserDetailsServiceImpl(
    private val getAccountUseCase: GetAccountUseCase
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserPrincipal? {
        val input = GetAccountInput(PrincipalName(username))
        val output = getAccountUseCase.execute(input)
        if (output.account == null) return null

        return UserPrincipal(
            userId = output.account.userId,
            accountId = output.account.userAccountId,
            principalName = output.account.principalName,
            password = output.account.userAccountPassword,
            accountLocked = output.account.isLocked(),
            accountEnabled = output.account.isEnabled(),
            accountExpired = false,
            credentialExpired = false
        )
    }
}
