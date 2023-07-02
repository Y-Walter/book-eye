package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    val userId: Long,
    val accountId: Long,
    val principalName: String,
    private val password: String,
    private val accountExpired: Boolean,
    private val accountLocked: Boolean,
    private val credentialExpired: Boolean,
    private val accountEnabled: Boolean
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = mutableListOf()

    override fun getPassword(): String = password

    override fun getUsername(): String = principalName

    override fun isAccountNonExpired(): Boolean = !accountExpired

    override fun isAccountNonLocked(): Boolean = !accountLocked

    override fun isCredentialsNonExpired(): Boolean = !credentialExpired

    override fun isEnabled(): Boolean = accountEnabled
}
