@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPassword.readOnce]
 */
class UserAccountPassword_readOnce : BehaviorSpec({
    Given("正常系") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)
        When("1回目の呼び出しの場合") {
            val act = sut.readOnce()
            Then("値が取得できること") {
                act shouldBe value
            }
        }
    }

    Given("複数呼び出し") {
        val value = "$2a$10$" + "a".repeat(53)
        val sut = UserAccountPassword(value)
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
