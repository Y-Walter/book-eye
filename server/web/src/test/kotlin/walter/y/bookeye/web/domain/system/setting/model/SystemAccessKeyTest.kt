package walter.y.bookeye.web.domain.system.setting.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldNotBeInRange
import io.kotest.matchers.string.shouldMatch
import io.kotest.matchers.string.shouldNotMatch

/**
 * [SystemAccessKey] 初期状態
 */
class SystemAccessKeyTest : BehaviorSpec({
    Given("NULL") {
        When("NULLの場合") {
            val systemKey = null

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }

    Given("Blank") {
        When("半角スペースのみの場合") {
            val systemKey = " ".repeat(256)
            systemKey.length shouldBeInRange 24..256

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("全角スペースのみの場合") {
            val systemKey = "　".repeat(256)
            systemKey.length shouldBeInRange 24..256

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("半角全角スペースのみの場合") {
            val systemKey = " 　".repeat(128)
            systemKey.length shouldBeInRange 24..256

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }

    Given("文字数") {
        When("23文字の場合") {
            val systemKey = "a".repeat(23)
            systemKey.length shouldNotBeInRange 24..256
            systemKey shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("24文字の場合") {
            val systemKey = "a".repeat(24)
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("256文字の場合") {
            val systemKey = "a".repeat(256)
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("257文字の場合") {
            val systemKey = "a".repeat(257)
            systemKey.length shouldNotBeInRange 24..256
            systemKey shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("トリミング前25文字で、トリミング後23文字の場合") {
            val systemKey = "　" + "a".repeat(23) + " "
            systemKey.length shouldBeInRange 24..256
            systemKey.trim().length shouldNotBeInRange 24..256
            systemKey.trim() shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("トリミング前26文字で、トリミング後24文字の場合") {
            val systemKey = "　" + "a".repeat(24) + " "
            systemKey.length shouldBeInRange 24..256
            systemKey.trim().length shouldBeInRange 24..256
            systemKey.trim() shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("トリミング前258文字で、トリミング後256文字の場合") {
            val systemKey = "　" + "a".repeat(256) + " "
            systemKey.length shouldNotBeInRange 24..256
            systemKey.trim().length shouldBeInRange 24..256
            systemKey.trim() shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }

    Given("文字種") {
        When("英字のみの場合") {
            val systemKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch ALPHABET_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("数字のみの場合") {
            val systemKey = "012345678901234567890123456789"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch NUMBER_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("記号のみの場合") {
            val systemKey = """_-+*~`'"&%$@!.,_-+*~`'"&%$@!.,_-+*~`'"&%$@!.,"""
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch SIGNAL_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("英数字の場合") {
            val systemKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch ALPHABET_NUMBER_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("利用可能な文字種をすべて使用した場合") {
            val systemKey = """abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-+*~`'"&%$@!.,"""
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("途中に半角スペースが含まれる場合") {
            val systemKey = """abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-+*~`'"&%$@!.,"""
            systemKey.length shouldBeInRange 24..256
            systemKey shouldNotMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("途中に全角スペースが含まれる場合") {
            val systemKey = """abcdefghijklmnopqrstuvwxyz　ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-+*~`'"&%$@!.,"""
            systemKey.length shouldBeInRange 24..256
            systemKey shouldNotMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("全角数字が含まれる場合") {
            When("全角数字を含む場合") {
                val systemKey = "UnitTestSystemKey２000001"
                systemKey.length shouldBeInRange 24..256
                systemKey shouldNotMatch SYSTEM_ACCESS_KEY_PATTERN

                Then("インスタンスの生成に失敗すること") {
                    shouldThrow<IllegalArgumentException> {
                        SystemAccessKey(systemKey)
                    }
                }
            }
        }
        When("日本語が含まれる場合") {
            val systemKey = "ユニットTestSystemKey0000001"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldNotMatch SYSTEM_ACCESS_KEY_PATTERN

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }
}) {
    companion object {
        private const val ALPHABET_PATTERN = """[a-zA-Z]+"""
        private const val NUMBER_PATTERN = """\d+"""
        private const val ALPHABET_NUMBER_PATTERN = """[a-zA-Z\d]+"""
        private const val SIGNAL_PATTERN = """[_\-+*~`'"&%\$@!.,]+"""
        private const val SYSTEM_ACCESS_KEY_PATTERN = """[a-zA-Z\d_\-+*~`'"&%\$@!.,]+"""
    }
}
