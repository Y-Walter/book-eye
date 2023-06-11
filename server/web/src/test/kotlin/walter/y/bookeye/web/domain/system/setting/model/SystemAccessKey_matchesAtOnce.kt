@file:Suppress("ClassName")

package walter.y.bookeye.web.domain.system.setting.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

/**
 * [SystemAccessKey.matchesAtOnce]
 */
class SystemAccessKey_matchesAtOnce : BehaviorSpec({
    Given("正常系") {
        When("比較対象と一致する場合") {
            val key = "UnitTestSystemKey0000001"
            val systemAccessKey = SystemAccessKey(key)
            val otherSystemAccessKey = SystemAccessKey(key)
            Then("戻り値がtrueになること") {
                val act = systemAccessKey.matchesAtOnce(otherSystemAccessKey)
                act shouldBe true
            }
        }
        When("比較対象と一致する場合") {
            val key = "UnitTestSystemKey0000001"
            val otherKey = "UnitTestSystemKey0000002"
            key shouldNotBe otherKey

            val systemAccessKey = SystemAccessKey(key)
            val otherSystemAccessKey = SystemAccessKey(otherKey)
            Then("戻り値がfalseになること") {
                val act = systemAccessKey.matchesAtOnce(otherSystemAccessKey)
                act shouldBe false
            }
        }
    }
    Given("異常系") {
        When("同じインスタンスで複数回使用する場合") {
            val key = "UnitTestSystemKey0000001"
            val systemAccessKey = SystemAccessKey(key)
            val otherSystemAccessKey = SystemAccessKey(key)
            Then("1回目は使用できること") {
                assertDoesNotThrow {
                    systemAccessKey.matchesAtOnce(otherSystemAccessKey)
                }
            }
            Then("2回目は使用できないこと") {
                assertThrows<IllegalStateException> {
                    systemAccessKey.matchesAtOnce(otherSystemAccessKey)
                }
            }
            Then("3回目も使用できないこと") {
                assertThrows<IllegalStateException> {
                    systemAccessKey.matchesAtOnce(otherSystemAccessKey)
                }
            }
        }
    }
})
