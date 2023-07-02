package walter.y.bookeye.web.domain.user.account.model

import walter.y.bookeye.web.domain.user.model.UserId

class UserAccountEntity(
    val userId: UserId,
    val userAccountId: UserAccountId,
    val principalName: PrincipalName,
    val userAccountPassword: UserAccountPassword,
    private val isEnabled: Boolean
) {
    fun isLocked(): Boolean = false

    fun isEnabled(): Boolean = isEnabled
}
