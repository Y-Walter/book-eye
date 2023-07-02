package walter.y.bookeye.web.domain.user.account.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * [PrincipalName] 初期状態
 */
class PrincipalNameTest : BehaviorSpec({
    Given("NULL") {
        When("NULLの場合") {
            val value = null
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("トリミング") {
        When("前後に空白が含まれない場合") {
            val value = "principal.name1@example.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功して、valueの値が変わらないこと") {
                act.value shouldBe value
            }
        }
        When("前後に空白が含まれる場合") {
            val value = "principal.name1@example.com"
            val act = PrincipalName(" $value　")
            Then("インスタンスの生成に成功して、valueの値がトリミングされていること") {
                act.value shouldBe value
            }
        }
        When("空白のみの場合") {
            val value = "      　　　　"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("全体の文字長") {
        When("7文字の場合") {
            val value = "b@a.com"
            value.length shouldBe 7
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("8文字の場合") {
            val value = "cb@a.com"
            value.length shouldBe 8
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("254文字の場合") {
            val value = "b".repeat(60) + "@" + "a".repeat(189) + ".com"
            value.length shouldBe 254
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("255文字の場合") {
            val value = "b".repeat(60) + "@" + "a".repeat(190) + ".com"
            value.length shouldBe 255
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("10000文字の場合") {
            val value = "b".repeat(60) + "@" + "a".repeat(9935) + ".com"
            value.length shouldBe 10000
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("@") {
        When("@を含む場合") {
            val value = "abcde@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("@を含まない場合") {
            val value = "abcde.test.com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("local-part の長さ") {
        When("0文字の場合") {
            val value = "@test.com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("1文字の場合") {
            val value = "b@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("64文字の場合") {
            val value = "b".repeat(64) + "@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("65文字の場合") {
            val value = "b".repeat(65) + "@test.com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("domain の長さ") {
        When("0文字の場合") {
            val value = "abcdefg@"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("1文字の場合") {
            val value = "abcdefg@a"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("2文字の場合") {
            val value = "abcdefg@ab"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("3文字の場合") {
            val value = "abcdefg@a.b"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
    }

    Given("local-part の文字種") {
        When("半角英小文字のみの場合") {
            val value = "abcdefghijklmnopqrstuvwxyz@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("半角英大文字のみの場合") {
            val value = "ABCDEFGHIJKLMNOPQRSTUVWXYZ@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("半角数字のみの場合") {
            val value = "0123456789@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("ドット以外の半角記号のみの場合") {
            val value = "!#$%&'*+/=?^_`{|}~-@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("半角英字・半角数字・半角記号が混在する場合") {
            val value = "abc-DEF_01234@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("ドットつなぎをする場合") {
            val value = "abc-DEF.98765.4321@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("連続でドットを使った場合") {
            val value = "abc-DEF..98765.4321@test.com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
    }

    Given("domain の文字種") {
        When("ドットなしの場合") {
            val value = "abcdefgh@com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("ドットつなぎの場合") {
            val value = "abcdefgh@test.com"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("ドットスタートの場合") {
            val value = "abcdefgh@.com"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("ドット終わりの場合") {
            val value = "abcdefgh@com."
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("半角数字の場合") {
            val value = "abcdefgh@0123.456"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("半角英小文字の場合") {
            val value = "abcdefgh@abc.efg"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("半角英大文字の場合") {
            val value = "abcdefgh@ABC.EFG"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
        When("先頭がハイフンの場合") {
            val value = "abcdefgh@-abc.efg"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("ドット区切りの単語の先頭がハイフンの場合") {
            val value = "abcdefgh@abc.-efg"
            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    PrincipalName(value)
                }
            }
        }
        When("ドット区切りの単語の中間がハイフンの場合") {
            val value = "abcdefgh@a-bc.e--fg"
            val act = PrincipalName(value)
            Then("インスタンスの生成に成功すること") {
                act.value shouldBe value
            }
        }
    }
})
