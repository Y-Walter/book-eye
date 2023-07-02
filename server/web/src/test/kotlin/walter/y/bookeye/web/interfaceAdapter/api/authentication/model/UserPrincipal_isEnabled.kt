@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserPrincipal.isEnabled]
 */
class UserPrincipal_isEnabled : BehaviorSpec({
    Given("正常系") {
        When("accountEnabled が true の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = mockk(relaxed = true),
                accountEnabled = true,
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isEnabled
            Then("true が返ること") {
                act shouldBe true
            }
        }
        When("accountEnabled が false の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = mockk(relaxed = true),
                accountEnabled = false,
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isEnabled
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
