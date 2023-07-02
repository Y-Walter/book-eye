@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserPrincipal.isCredentialsNonExpired]
 */
class UserPrincipal_isCredentialsNonExpired : BehaviorSpec({
    Given("正常系") {
        When("credentialExpired が true の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = mockk(relaxed = true),
                accountEnabled = mockk(relaxed = true),
                credentialExpired = true
            )
            val act = sut.isCredentialsNonExpired
            Then("false が返ること") {
                act shouldBe false
            }
        }
        When("credentialExpired が false の場合") {
            val sut = UserPrincipal(
                userId = mockk(),
                accountId = mockk(),
                principalName = mockk(relaxed = true),
                password = mockk(relaxed = true),
                accountExpired = mockk(relaxed = true),
                accountLocked = mockk(relaxed = true),
                accountEnabled = mockk(relaxed = true),
                credentialExpired = false
            )
            val act = sut.isAccountNonLocked
            Then("true が返ること") {
                act shouldBe true
            }
        }
    }
})
