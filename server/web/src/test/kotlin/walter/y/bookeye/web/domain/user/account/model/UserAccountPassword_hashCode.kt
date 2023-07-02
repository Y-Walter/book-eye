@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPassword.hashCode]
 */
class UserAccountPassword_hashCode : BehaviorSpec({
    Given("正常系") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)
        When("hashCode を実行した場合") {
            val act = sut.hashCode()
            Then("value をhash化した値と一致すること") {
                act shouldBe value.hashCode()
            }
        }
    }
})
