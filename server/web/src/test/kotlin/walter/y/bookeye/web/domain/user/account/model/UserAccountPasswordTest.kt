package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPassword] 初期状態
 */
class UserAccountPasswordTest : BehaviorSpec({
    Given("NULL") {
        When("NULLの場合") {
            val value = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPassword(value)
                }
            }
        }
    }

    Given("トリミング") {
        When("前後に空白を含まない場合") {
            val value = "$2a$10$" + "a".repeat(53)
            val act = UserAccountPassword(value)
            Then("インスタンスの生成に成功して、値が変わらないこと") {
                act.readOnce() shouldBe value
            }
        }
        When("前後に空白を含む場合") {
            val value = "$2a$10$" + "a".repeat(53)
            val act = UserAccountPassword(" $value　")
            Then("インスタンスの生成に成功して、値がトリミングされていること") {
                act.readOnce() shouldBe value
            }
        }
        When("空白のみの場合") {
            val value = " ".repeat(60)
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPassword(value)
                }
            }
        }
    }

    Given("文字長") {
        When("59文字の場合") {
            val value = "$2a$10$" + "a".repeat(52)
            value.length shouldBe 59
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPassword(value)
                }
            }
        }
        When("60文字の場合") {
            val value = "$2a$10$" + "a".repeat(53)
            value.length shouldBe 60
            val act = UserAccountPassword(value)
            Then("インスタンスの生成に成功して、値がトリミングされていること") {
                act.readOnce() shouldBe value
            }
        }
        When("61文字の場合") {
            val value = "$2a$10$" + "a".repeat(54)
            value.length shouldBe 61
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPassword(value)
                }
            }
        }
    }
})
