@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

/**
 * [UserAccountEntity.isLocked]
 */
class UserAccountEntity_isLocked : BehaviorSpec({
    Given("正常系") {
        When("isEnabled を true で指定した場合") {
            val isEnabled = true
            val sut = UserAccountEntity(
                userId = mockk(),
                userAccountId = mockk(),
                principalName = mockk(),
                userAccountPassword = mockk(),
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
                userId = mockk(),
                userAccountId = mockk(),
                principalName = mockk(),
                userAccountPassword = mockk(),
                isEnabled = isEnabled
            )
            val act = sut.isLocked()
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
