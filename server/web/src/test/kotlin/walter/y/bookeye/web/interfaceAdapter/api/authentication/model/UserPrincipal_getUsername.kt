@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import walter.y.bookeye.web.domain.user.account.model.PrincipalName

/**
 * [UserPrincipal.getUsername]
 */
class UserPrincipal_getUsername : BehaviorSpec({
    Given("正常系") {
        val principalName = PrincipalName("principal.name1@test.com")
        val sut = UserPrincipal(
            userId = mockk(),
            accountId = mockk(),
            principalName = principalName,
            password = mockk(relaxed = true),
            accountExpired = mockk(relaxed = true),
            accountLocked = mockk(relaxed = true),
            accountEnabled = mockk(relaxed = true),
            credentialExpired = mockk(relaxed = true)
        )
        When("メソッドを実行した場合") {
            val act = sut.username
            Then("PrincipalNameの値が返ってくること") {
                act shouldBe principalName.value
            }
        }
    }
})
