package walter.y.bookeye.web.domain.system.setting.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.BehaviorSpec

/**
 * [SystemSettingEntity] 初期状態
 */
class SystemSettingEntityTest : BehaviorSpec({
    Given("正常系") {
        When("インスタンスの生成時") {
            val accessKey = SystemAccessKey("a".repeat(256))
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    SystemSettingEntity(systemAccessKey = accessKey)
                }
            }
        }
    }
})
