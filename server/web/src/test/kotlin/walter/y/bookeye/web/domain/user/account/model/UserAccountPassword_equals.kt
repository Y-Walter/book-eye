@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * [UserAccountPassword.equals]
 */
class UserAccountPassword_equals : BehaviorSpec({

    Given("other is NULL") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)

        When("NULL を比較した場合") {
            val other = null
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is not type of UserAccountPassword") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)
        When("String 型を比較した場合") {
            val act = sut.equals(value)
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is type of UserAccountPassword") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)

        When("同一の値を比較した場合") {
            val other = UserAccountPassword(value)
            val act = sut == other
            Then("true が返ること") {
                act shouldBe true
            }
        }

        When("異なる値を比較した場合") {
            val otherValue = "$2a$10$" + "b".repeat(53)
            val other = UserAccountPassword(otherValue)
            value shouldNotBe otherValue
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
