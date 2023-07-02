package walter.y.bookeye.web.domain.user.account.repository

import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity

interface UserAccountRepository {
    fun getOrNull(principalName: PrincipalName): UserAccountEntity?
}
