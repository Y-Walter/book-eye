@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.password

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [PasswordStrength.hashCode]
 */
class PasswordStrength_hashCode : BehaviorSpec({
    Given("正常系") {
        val value = 10
        val sut = PasswordStrength(value)
        When("hashCode を実行した場合") {
            val act = sut.hashCode()
            Then("value をハッシュ化した値と一致すること") {
                act shouldBe value.hashCode()
            }
        }
    }
})
