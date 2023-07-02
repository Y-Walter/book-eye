@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

/**
 * [PrincipalName.equals]
 */
class PrincipalName_equals : BehaviorSpec({
    Given("other is NULL") {
        val sut = PrincipalName("principal.name1@example.com")
        When("NULLを比較した場合") {
            val other = null
            val act = sut == other
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is not type of PrincipalName") {
        val value = "principal.name1@example.com"
        val sut = PrincipalName(value)
        When("String型を比較した場合") {
            val act = sut.equals(value)
            Then("false が返ること") {
                act shouldBe false
            }
        }
    }

    Given("other is type of PrincipalName") {
        val value = "principal.name1@example.com"
        val sut = PrincipalName(value)
        When("同一の value を比較した場合") {
            val other = PrincipalName(value)
            val act = sut == other
            Then("true が返ること") {
                act shouldBe true
            }
        }
        When("異なる value を比較した場合") {
            val otherValue = "principal.name2@example.com"
            val other = PrincipalName(otherValue)
            val act = sut == other
            Then("false が返ること") {
                value shouldNotBe otherValue
                act shouldBe false
            }
        }
    }
})
