@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import walter.y.bookeye.web.domain.user.model.UserId

/**
 * [UserAccountEntity.isLocked]
 */
class UserAccountEntity_isLocked : BehaviorSpec({
    Given("正常系") {
        val userId = UserId(1L)
        val userAccountId = UserAccountId(1L)
        val principalName = PrincipalName("principal.name1@test.com")
        val accountPassword = UserAccountPassword.Builder(
            source = UserAccountPasswordSource("Unit-Test-123"),
            passwordEncoder = BCryptPasswordEncoder()
        ).build()

        When("isEnabled を true で指定した場合") {
            val isEnabled = true
            val sut = UserAccountEntity(
                userId = userId,
                userAccountId = userAccountId,
                principalName = principalName,
                userAccountPassword = accountPassword,
                isEnabled = isEnabled
            )
            val act = sut.isLocked()
            Then("false が返ること") {
                act shouldBe false
            }
        }
        When("isEnabled を false で指定した場合") {
            val isEnabled = false
            val sut = UserAccountEntity(
                userId = userId,
                userAccountId = userAccountId,
                principalName = principalName,
                userAccountPassword = accountPassword,
                isEnabled = isEnabled
            )
            val act = sut.isLocked()
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
