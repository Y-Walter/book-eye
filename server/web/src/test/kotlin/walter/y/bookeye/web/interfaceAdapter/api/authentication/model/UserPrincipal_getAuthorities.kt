@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserPrincipal.getAuthorities]
 */
class UserPrincipal_getAuthorities : BehaviorSpec({
    Given("正常系") {
        val sut = UserPrincipal(
            userId = mockk(),
            accountId = mockk(),
            principalName = mockk(relaxed = true),
            password = mockk(relaxed = true),
            accountExpired = mockk(relaxed = true),
            accountLocked = mockk(relaxed = true),
            accountEnabled = mockk(relaxed = true),
            credentialExpired = mockk(relaxed = true)
        )
        When("メソッドを実行した場合") {
            val act = sut.authorities
            Then("空のMutableCollectionが返ること") {
                act shouldBe mutableListOf()
            }
        }
    }
})
