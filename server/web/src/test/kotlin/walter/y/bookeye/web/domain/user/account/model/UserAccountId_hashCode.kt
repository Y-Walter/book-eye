@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountId.hashCode]
 */
class UserAccountId_hashCode : BehaviorSpec({
    Given("正常系") {
        val value = 1L
        val sut = UserAccountId(value)
        When("hashCodeを実行した場合") {
            val act = sut.hashCode()
            Then("value の値をhash化したものと同じであること") {
                act shouldBe value.hashCode()
            }
        }
    }
})
