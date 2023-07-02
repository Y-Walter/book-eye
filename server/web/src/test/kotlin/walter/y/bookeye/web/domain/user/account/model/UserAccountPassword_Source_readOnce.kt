@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPassword.Source.readOnce]
 */
class UserAccountPassword_Source_readOnce : BehaviorSpec({
    Given("正常系") {
        val value = "Unit-Test-123"
        val sut = UserAccountPassword.Source(value)
        When("1回目の呼び出しの場合") {
            val act = sut.readOnce()
            Then("値を取得できること") {
                act shouldBe value
            }
        }
    }

    Given("異常系") {
        val value = "Unit-Test-123"
        val sut = UserAccountPassword.Source(value)
        sut.readOnce()
        When("2回目の呼び出しの場合") {
            Then("値の取得に失敗すること") {
                shouldThrow<IllegalStateException> {
                    sut.readOnce()
                }
            }
        }
        When("3回目の呼び出しの場合") {
            Then("値の取得に失敗すること") {
                shouldThrow<IllegalStateException> {
                    sut.readOnce()
                }
            }
        }
    }
})
