package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [UserAccountPasswordSource] 初期状態
 */
class UserAccountPasswordSourceTest : BehaviorSpec({
    Given("正常系") {
        When("パスワードポリシーを満たす場合") {
            val value = "Unit-Test-123"
            val act = UserAccountPasswordSource(value)
            Then("インスタンスの生成に成功すること") {
                act.readOnce() shouldBe value
            }
        }
    }

    Given("NULL") {
        When("NULL の場合") {
            val value = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
    }

    Given("トリミング") {
        When("前後に空白が含まれる場合") {
            val value = "Unit-Test-123"
            val act = UserAccountPasswordSource(" $value　")
            Then("インスタンスの生成に成功して、valueの値がトリミングされていること") {
                act.readOnce() shouldBe value
            }
        }
        When("空白のみの場合") {
            val value = "      　　　　"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
    }

    Given("文字長") {
        When("7文字の場合") {
            val value = "Unit123"
            value.length shouldBe 7
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("8文字の場合") {
            val value = "Unit1234"
            value.length shouldBe 8
            val act = UserAccountPasswordSource(value)
            Then("インスタンスの生成に成功すること") {
                act.readOnce() shouldBe value
            }
        }
        When("64文字の場合") {
            val value = "Unit1234" + "a".repeat(56)
            value.length shouldBe 64
            val act = UserAccountPasswordSource(value)
            Then("インスタンスの生成に成功すること") {
                act.readOnce() shouldBe value
            }
        }
        When("65文字の場合") {
            val value = "Unit1234" + "a".repeat(57)
            value.length shouldBe 65
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("10000文字の場合") {
            val value = "Unit1234" + "a".repeat(9992)
            value.length shouldBe 10000
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
    }

    Given("文字種") {
        When("半角英小文字のみの場合") {
            val value = "abcdefghijklmnopqrstuvwxyz"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("半角英大文字のみの場合") {
            val value = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("半角数字のみの場合") {
            val value = "0123456789"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("記号のみの場合") {
            val value = """!@#$%^&*()-_=+[{]};:'",<.>/?"""
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("半角英小文字・半角英大文字・半角数字を1文字以上使用した場合") {
            val value = "Abcdefg0123"
            val act = UserAccountPasswordSource(value)
            Then("インスタンスの生成に成功すること") {
                act.readOnce() shouldBe value
            }
        }
        When("半角英小文字・半角英大文字・半角数字・記号を1文字以上使用した場合") {
            val value = "Abcdefg0123-"
            val act = UserAccountPasswordSource(value)
            Then("インスタンスの生成に成功すること") {
                act.readOnce() shouldBe value
            }
        }
        When("半角英小文字・半角数字を1文字以上使用した場合") {
            val value = "bcdefg0123"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("半角英大文字・半角数字を1文字以上使用した場合") {
            val value = "ABCDEFG0123"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
        When("半角英小文字・半角英大文字を1文字以上使用した場合") {
            val value = "abcdefgABCDEFG"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    UserAccountPasswordSource(value)
                }
            }
        }
    }
})
