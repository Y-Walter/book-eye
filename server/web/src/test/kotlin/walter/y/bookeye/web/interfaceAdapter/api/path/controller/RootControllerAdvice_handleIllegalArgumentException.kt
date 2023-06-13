@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

/**
 * [RootControllerAdvice.handleIllegalArgumentException]
 */
class RootControllerAdvice_handleIllegalArgumentException : BehaviorSpec({
    Given("正常系") {
        val sut = RootControllerAdvice()
        When("IllegalArgumentException を検知した場合") {
            val ex = IllegalArgumentException("illegal argument")
            val act = sut.handleIllegalArgumentException(ex)
            Then("ステータスコードが 400 になること") {
                act.statusCode shouldBe HttpStatus.BAD_REQUEST
            }
            Then("errorCode が INVALID_ARGUMENT になること") {
                act.body shouldNotBe null
                act.body!!.errorCode shouldBe ApiErrorResDTO.ErrorCode.INVALID_ARGUMENT
            }
        }
    }
})
