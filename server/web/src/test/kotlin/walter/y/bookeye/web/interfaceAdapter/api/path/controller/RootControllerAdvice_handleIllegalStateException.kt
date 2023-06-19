@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

/**
 * [RootControllerAdvice.handleIllegalStateException]
 */
class RootControllerAdvice_handleIllegalStateException : BehaviorSpec({
    Given("正常系") {
        val sut = RootControllerAdvice()
        When("IllegalStateException を検知した場合") {
            val ex = IllegalStateException("illegal state")
            val act = sut.handleIllegalStateException(ex)
            Then("ステータスコードが 500 になること") {
                act.statusCode shouldBe HttpStatus.INTERNAL_SERVER_ERROR
            }
            Then("errorCode が UNKNOWN になること") {
                act.body shouldNotBe null
                act.body!!.errorCode shouldBe ApiErrorResDTO.ErrorCode.UNKNOWN
            }
        }
    }
})
