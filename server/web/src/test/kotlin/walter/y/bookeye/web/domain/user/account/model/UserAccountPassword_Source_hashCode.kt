@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPassword.Source.hashCode]
 */
class UserAccountPassword_Source_hashCode : BehaviorSpec({
    Given("正常系") {
        val value = "Unit-Test-123"
        val sut = UserAccountPassword.Source(value)
        When("hashCode を実行した場合") {
            val act = sut.hashCode()
            Then("valueをhash化したものと一致すること") {
                act shouldBe value.hashCode()
            }
        }
    }
})
