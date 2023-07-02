package walter.y.bookeye.web.domain.user.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserId] 初期状態
 */
class UserIdTest : BehaviorSpec({
    Given("正常値") {
        When("100の場合") {
            val value = 100L
            Then("インスタンスの生成に成功すること") {
                val act = UserId(value)
                act.value shouldBe value
            }
        }
    }

    Given("NULL") {
        When("NULLの場合") {
            val value = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserId(value)
                }
            }
        }
    }

    Given("数値") {
        When("${Long.MIN_VALUE}の場合") {
            val value = Long.MIN_VALUE
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserId(value)
                }
            }
        }
        When("-1の場合") {
            val value = -1L
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserId(value)
                }
            }
        }
        When("0の場合") {
            val value = 0L
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserId(value)
                }
            }
        }
        When("1の場合") {
            val value = 1L
            Then("インスタンスの生成に成功すること") {
                val act = UserId(value)
                act.value shouldBe value
            }
        }
        When("${Long.MAX_VALUE}の場合") {
            val value = Long.MAX_VALUE
            Then("インスタンスの生成に成功すること") {
                val act = UserId(value)
                act.value shouldBe value
            }
        }
    }
})
