@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * [UserAccountPassword.Builder.build]
 */
class UserAccountPassword_Builder_build : BehaviorSpec({
    Given("正常系") {
        val value = "Unit-Test-123"
        val passwordSource = UserAccountPasswordSource(value)
        val passwordEncoder = BCryptPasswordEncoder()
        val sut = UserAccountPassword.Builder(
            source = passwordSource,
            passwordEncoder = passwordEncoder
        )
        When("buildを実行した場合") {
            val act = sut.build()
            Then("値がハッシュ化されていること") {
                passwordEncoder.matches(value, act.readOnce()) shouldBe true
            }
        }
    }
})
