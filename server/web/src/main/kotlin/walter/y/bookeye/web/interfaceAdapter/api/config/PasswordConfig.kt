package walter.y.bookeye.web.interfaceAdapter.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import walter.y.bookeye.web.domain.password.PasswordStrength

@ConfigurationProperties(prefix = "app.api.password")
class PasswordConfig(
    bcryptStrength: Int
) {
    val bcryptStrength = PasswordStrength(bcryptStrength)
}
