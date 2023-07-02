@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * [UserAccountPassword_equals.Source.equals]
 */
class UserAccountPasswordSource_equals : BehaviorSpec({
    Given("other is NULL") {
        val value = "Unit-Test-123"
        val sut = UserAccountPasswordSource(value)
        When("NULL を比較した場合") {
            val other = null
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is not type of UserAccountPasswordSource") {
        val value = "Unit-Test-123"
        val sut = UserAccountPasswordSource(value)
        When("String 型を比較した場合") {
            val act = sut.equals(value)
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is type of UserAccountPasswordSource") {
        val value = "Unit-Test-123"
        val sut = UserAccountPasswordSource(value)
        When("同一の値をを比較した場合") {
            val other = UserAccountPasswordSource(value)
            val act = sut == other
            Then("true が返ること") {
                act shouldBe true
            }
        }
        When("異なる値をを比較した場合") {
            val otherValue = "Unit-Test-1234"
            val other = UserAccountPasswordSource(otherValue)
            value shouldNotBe otherValue
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
