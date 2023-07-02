@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword

/**
 * [UserPrincipal.getPassword]
 */
class UserPrincipal_getPassword : BehaviorSpec({
    Given("正常系") {
        val password = "$2a$10$" + "a".repeat(53)
        val sut = UserPrincipal(
            userId = mockk(),
            accountId = mockk(),
            principalName = mockk(relaxed = true),
            password = UserAccountPassword(password),
            accountExpired = mockk(relaxed = true),
            accountLocked = mockk(relaxed = true),
            accountEnabled = mockk(relaxed = true),
            credentialExpired = mockk(relaxed = true)
        )
        When("1回目にメソッドを実行した場合") {
            val act = sut.password
            Then("パスワードを取得できること") {
                act shouldBe password
            }
        }
    }

    Given("異常系") {
        val password = "$2a$10$" + "a".repeat(53)
        val sut = UserPrincipal(
            userId = mockk(),
            accountId = mockk(),
            principalName = mockk(relaxed = true),
            password = UserAccountPassword(password),
            accountExpired = mockk(relaxed = true),
            accountLocked = mockk(relaxed = true),
            accountEnabled = mockk(relaxed = true),
            credentialExpired = mockk(relaxed = true)
        )
        sut.password
        When("複数回メソッドを実行した場合") {
            Then("IllegalStateException が発生すること") {
                shouldThrow<IllegalStateException> {
                    sut.password
                }
            }
        }
    }
})
