package walter.y.bookeye.web.interfaceAdapter.repository.user.account

import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.account.repository.UserAccountRepository
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.mapper.UserAccountMapper

//@Repository
class UserAccountRepositoryImpl(
    private val userAccountMapper: UserAccountMapper
) : UserAccountRepository {
    override fun getOrNull(principalName: PrincipalName): UserAccountEntity? {
        val userAccount = userAccountMapper.findByPrincipalName(principalName = principalName.value)
            ?: return null
        return UserAccountEntity(
            userId = UserId(userAccount.userId),
            userAccountId = UserAccountId(userAccount.userAccountId),
            principalName = PrincipalName(userAccount.principalName),
            userAccountPassword = UserAccountPassword(userAccount.accountPassword),
            isEnabled = userAccount.isEnabled
        )
    }
}
