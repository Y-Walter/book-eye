@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import walter.y.bookeye.web.interfaceAdapter.gateway.env.config.GatewayEnvConfig

/**
 * [SystemVersionClientImpl.getVersion]
 */
class SystemVersionClientImpl_getVersion : BehaviorSpec({
    Given("正常系") {
        val mockGatewayEnvConfig = mockk<GatewayEnvConfig>()
        val sut = SystemVersionClientImpl(mockGatewayEnvConfig)
        When("メソッドを実行した場合") {
            val versionValue = "1.2.3"
            every {
                mockGatewayEnvConfig.systemVersion
            } returns SystemVersion(versionValue)
            val act = sut.getVersion()
            Then("バージョンを取得できること") {
                act.value shouldBe versionValue
            }
        }
    }
})
