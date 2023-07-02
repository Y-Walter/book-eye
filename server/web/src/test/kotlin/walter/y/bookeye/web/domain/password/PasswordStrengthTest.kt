package walter.y.bookeye.web.domain.password

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [PasswordStrength] 初期状態
 */
class PasswordStrengthTest : BehaviorSpec({

    Given("NULL") {
        When("NULLの場合") {
            val value = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
    }

    Given("数値") {
        When("Int型最小値 の場合") {
            val value = Int.MIN_VALUE
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
        When("-1 の場合") {
            val value = -1
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
        When("0 の場合") {
            val value = 0
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
        When("3 の場合") {
            val value = 3
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
        When("4 の場合") {
            val value = 4
            Then("インスタンスの生成に成功すること") {
                val act = shouldNotThrowAny {
                    PasswordStrength(value)
                }
                act.value shouldBe value
            }
        }
        When("10 の場合") {
            val value = 10
            Then("インスタンスの生成に成功すること") {
                val act = shouldNotThrowAny {
                    PasswordStrength(value)
                }
                act.value shouldBe value
            }
        }
        When("31 の場合") {
            val value = 31
            Then("インスタンスの生成に成功すること") {
                val act = shouldNotThrowAny {
                    PasswordStrength(value)
                }
                act.value shouldBe value
            }
        }
        When("32 の場合") {
            val value = 32
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
        When("Int型最大値 の場合") {
            val value = Int.MAX_VALUE
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PasswordStrength(value)
                }
            }
        }
    }
})
