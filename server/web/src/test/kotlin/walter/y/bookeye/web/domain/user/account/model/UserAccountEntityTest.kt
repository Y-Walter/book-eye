package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import walter.y.bookeye.web.domain.user.model.UserId

/**
 * [UserAccountEntity] 初期状態
 */
class UserAccountEntityTest : BehaviorSpec({
    Given("正常系") {
        val userId = UserId(1L)
        val userAccountId = UserAccountId(1L)
        val principalName = PrincipalName("principal.name1@test.com")
        val accountPassword = UserAccountPassword.Builder(
            source = UserAccountPasswordSource("Unit-Test-123"),
            passwordEncoder = BCryptPasswordEncoder()
        ).build()

        When("インスタンス生成時") {
            Then("エラーが発生しないこと") {
                shouldNotThrow<Exception> {
                    UserAccountEntity(
                        userId = userId,
                        userAccountId = userAccountId,
                        principalName = principalName,
                        userAccountPassword = accountPassword,
                        isEnabled = true
                    )
                }
            }
        }
    }
})
