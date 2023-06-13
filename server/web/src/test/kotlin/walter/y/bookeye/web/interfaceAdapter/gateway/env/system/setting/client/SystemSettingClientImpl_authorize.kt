@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.interfaceAdapter.gateway.env.config.GatewayEnvConfig

/**
 * [SystemSettingClientImpl.authorize]
 */
class SystemSettingClientImpl_authorize : BehaviorSpec({
    Given("正常系") {
        val accessKeyValue = "unit-test-system-access-key"
        val gatewayEnvConfig = GatewayEnvConfig(
            systemSetting = GatewayEnvConfig.SystemSetting(
                accessKey = accessKeyValue
            )
        )
        val sut = SystemSettingClientImpl(gatewayEnvConfig)
        When("有効なSystemAccessKeyを指定した場合") {
            val systemAccessKey = SystemAccessKey(accessKeyValue)
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    sut.authorize(systemAccessKey)
                }
            }
        }
    }

    Given("異常系") {
        val accessKeyValue = "unit-test-system-access-key"
        val gatewayEnvConfig = GatewayEnvConfig(
            systemSetting = GatewayEnvConfig.SystemSetting(
                accessKey = accessKeyValue
            )
        )
        val sut = SystemSettingClientImpl(gatewayEnvConfig)
        When("無効なSystemAccessKeyを指定した場合") {
            val invalidAccessKeyValue = "invalid-unit-test-system-access-key"
            invalidAccessKeyValue shouldNotBe accessKeyValue
            val systemKey = SystemAccessKey(invalidAccessKeyValue)
            Then("SystemSettingClientException.Unauthorized を返すこと") {
                shouldThrow<SystemSettingClientException.Unauthorized> {
                    sut.authorize(systemKey)
                }
            }
        }
    }
})
