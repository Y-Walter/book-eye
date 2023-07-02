package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.model.UserId

data class UserPrincipal(
    val userId: UserId,
    val accountId: UserAccountId,
    val principalName: PrincipalName,
    private val password: UserAccountPassword,
    private val accountExpired: Boolean,
    private val accountLocked: Boolean,
    private val credentialExpired: Boolean,
    private val accountEnabled: Boolean
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = mutableListOf()

    override fun getPassword(): String = password.readOnce()

    override fun getUsername(): String = principalName.value

    override fun isAccountNonExpired(): Boolean = !accountExpired

    override fun isAccountNonLocked(): Boolean = !accountLocked

    override fun isCredentialsNonExpired(): Boolean = !credentialExpired

    override fun isEnabled(): Boolean = accountEnabled
}
