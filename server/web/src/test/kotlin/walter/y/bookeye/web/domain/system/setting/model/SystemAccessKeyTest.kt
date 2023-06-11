package walter.y.bookeye.web.domain.system.setting.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import io.kotest.matchers.string.shouldNotMatch

/**
 * [SystemAccessKey] 初期状態
 */
class SystemAccessKeyTest : BehaviorSpec({

    Given("正常系") {
        When("24文字の英数字の場合") {
            val systemKey = "UnitTestSystemKey0000001"
            systemKey.length shouldBe 24
            systemKey shouldMatch """[a-zA-Z\d]*"""

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("256文字の英数字の場合") {
            val systemKey = "UnitTestSystemKey00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"
            systemKey.length shouldBe 256
            systemKey shouldMatch """[a-zA-Z\d]*"""

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("英数字と許可された記号で構成した場合") {
            val systemKey = """UnitTestSystemKey0001_-+*~`'"&%$@!.,"""
            systemKey.length shouldBeInRange 24..256
            systemKey shouldMatch """[a-zA-Z\d_\-+*~`'"&%\$@!.,]*"""

            Then("インスタンスの生成ができること") {
                shouldNotThrowAny {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }

    Given("異常系") {
        When("23文字の場合") {
            val systemKey = "UnitTestSystemKey000001"
            systemKey.length shouldBe 23
            systemKey shouldMatch """[a-zA-Z\d_\-+*~`'"&%\$@!.,]*"""

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("257文字の場合") {
            val systemKey = "UnitTestSystemKey000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"
            systemKey.length shouldBe 257
            systemKey shouldMatch """[a-zA-Z\d_\-+*~`'"&%\$@!.,]*"""

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("全角数字を含む場合") {
            val systemKey = "UnitTestSystemKey２000001"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldNotMatch """[a-zA-Z\d_\-+*~`'"&%\$@!.,]*"""

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("日本語を含む場合") {
            val systemKey = "ユニットTestSystemKey0000001"
            systemKey.length shouldBeInRange 24..256
            systemKey shouldNotMatch """[a-zA-Z\d_\-+*~`'"&%\$@!.,]*"""

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
        When("NULLの場合") {
            val systemKey = null

            Then("インスタンスの生成に失敗すること") {
                shouldThrow<IllegalArgumentException> {
                    SystemAccessKey(systemKey)
                }
            }
        }
    }
})
