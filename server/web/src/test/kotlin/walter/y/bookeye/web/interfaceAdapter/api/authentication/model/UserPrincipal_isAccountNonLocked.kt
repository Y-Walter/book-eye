@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserPrincipal.isAccountNonLocked]
 */
class UserPrincipal_isAccountNonLocked : BehaviorSpec({
    Given("正常系") {
        When("accountLocked が true の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = true,
                accountEnabled = mockk(relaxed = true),
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isAccountNonLocked
            Then("false が返ること") {
                act shouldBe false
            }
        }
        When("accountLocked が false の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = false,
                accountEnabled = mockk(relaxed = true),
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isAccountNonLocked
            Then("true が返ること") {
                act shouldBe true
            }
        }
    }
})
