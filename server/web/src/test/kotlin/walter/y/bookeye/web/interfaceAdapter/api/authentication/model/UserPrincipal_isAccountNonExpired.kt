@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserPrincipal.isAccountNonExpired]
 */
class UserPrincipal_isAccountNonExpired : BehaviorSpec({
    Given("正常系") {
        When("accountExpired が true の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = true,
                accountLocked = mockk(relaxed = true),
                accountEnabled = mockk(relaxed = true),
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isAccountNonExpired
            Then("false が返ること") {
                act shouldBe false
            }
        }
        When("accountExpired が false の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = false,
                accountLocked = mockk(relaxed = true),
                accountEnabled = mockk(relaxed = true),
                credentialExpired = mockk(relaxed = true)
            )
            val act = sut.isAccountNonExpired
            Then("true が返ること") {
                act shouldBe true
            }
        }
    }
})
