package walter.y.bookeye.web.domain.system.version.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.BehaviorSpec

/**
 * [SystemVersionEntity] 初期状態
 */
class SystemVersionEntityTest : BehaviorSpec({
    Given("正常系") {
        When("インスタンス生成時") {
            val version = SystemVersion("1.2.3")
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    SystemVersionEntity(version = version)
                }
            }
        }
    }
})
