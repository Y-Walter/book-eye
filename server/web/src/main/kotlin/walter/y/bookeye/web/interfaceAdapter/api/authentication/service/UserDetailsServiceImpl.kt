package walter.y.bookeye.web.interfaceAdapter.api.authentication.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import walter.y.bookeye.web.interfaceAdapter.api.authentication.model.UserPrincipal

@Service
class UserDetailsServiceImpl(
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserPrincipal? {
        return UserPrincipal(
            userId = -1L,
            accountId = -1L,
            principalName = username,
            password = passwordEncoder.encode("password"),
            accountLocked = false,
            accountEnabled = true,
            accountExpired = false,
            credentialExpired = false
        )
    }
}
