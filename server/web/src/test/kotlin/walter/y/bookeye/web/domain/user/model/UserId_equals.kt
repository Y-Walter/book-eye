@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import walter.y.bookeye.web.domain.user.account.model.UserAccountId

/**
 * [UserId.equals]
 */
class UserId_equals : BehaviorSpec({
    Given("other is NULL") {
        val sut = UserId(1L)
        When("NULLを比較した場合") {
            val other = null
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is not type of UserId") {
        val value = 1L
        val sut = UserId(value)
        When("Long型を比較した場合") {
            val act = sut.equals(value)
            Then("false が返ること") {
                act shouldBe false
            }
        }
        When("UserAccountId型を比較した場合") {
            val other = UserAccountId(value)
            val act = sut.equals(other)
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is type of UserId") {
        val value = 1L
        val sut = UserId(value)
        When("同一のvalueを比較した場合") {
            val other = UserId(value)
            val act = sut == other
            Then("true が返ること") {
                act shouldBe true
            }
        }
        When("異なるvalueを比較した場合") {
            val other = UserId(value + 1)
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }
})
