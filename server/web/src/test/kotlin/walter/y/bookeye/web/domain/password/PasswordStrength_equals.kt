@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.password

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * [PasswordStrength.equals]
 */
class PasswordStrength_equals : BehaviorSpec({
    Given("other is NULL") {
        val value = 10
        val sut = PasswordStrength(value)
        When("NULL を比較した場合") {
            val other = null
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is not type of PasswordStrength") {
        val value = 10
        val sut = PasswordStrength(value)
        When("Int 型を比較した場合") {
            val act = sut.equals(value)
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
    Given("other is type of PasswordStrength") {
        val value = 10
        val sut = PasswordStrength(value)
        When("同一の値を比較した場合") {
            val other = PasswordStrength(value)
            val act = sut == other
            Then("true が返ること") {
                act shouldBe true
            }
        }
        When("異なる値を比較した場合") {
            val otherValue = 12
            val other = PasswordStrength(otherValue)
            value shouldNotBe otherValue
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
