@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.interfaceAdapter.api.path.system.model.HealthCheckResDTO

class SystemController_checkHealth : BehaviorSpec({
    Given("正常系") {
        val sut = SystemController(mockk())
        When("システムが正常な場合") {
            val act = sut.checkHealth()
            Then("ステータスコードが, 200 になること") {
                act.statusCode shouldBe HttpStatus.OK
            }
            Then("メッセージがSUCCESSFULであること") {
                act.body shouldBe HealthCheckResDTO("SUCCESSFUL")
            }
        }
    }
})
