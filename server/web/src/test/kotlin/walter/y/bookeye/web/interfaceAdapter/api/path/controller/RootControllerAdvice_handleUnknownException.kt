@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

/**
 * [RootControllerAdvice.handleUnknownException]
 */
class RootControllerAdvice_handleUnknownException : BehaviorSpec({
    Given("正常系") {
        val sut = RootControllerAdvice()
        When("Exception を検知した場合") {
            val ex = Exception("exception")
            val act = sut.handleUnknownException(ex)
            Then("ステータスコードは500になること") {
                act.statusCode shouldBe HttpStatus.INTERNAL_SERVER_ERROR
            }
            Then("errorCode は、UNKNOWN であること") {
                act.body shouldNotBe null
                act.body!!.errorCode shouldBe ApiErrorResDTO.ErrorCode.UNKNOWN
            }
        }
    }
})
