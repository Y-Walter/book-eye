@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.mockk
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

/**
 * [SystemControllerAdvice.handleSystemAccessKeyInvalidException]
 */
class SystemControllerAdvice_handleSystemAccessKeyInvalidException : BehaviorSpec({
    Given("正常系") {
        val sut = SystemControllerAdvice(mockk())
        When("SystemAccessKeyInvalidException を検知した場合") {
            val ex = ApiSystemAccessKeyInvalidException(null)
            val act = sut.handleSystemAccessKeyInvalidException(ex)
            Then("ステータスコードが、401 になること") {
                act.statusCode shouldBe HttpStatus.UNAUTHORIZED
            }
            Then("errorCode が UNAUTHORIZED になること") {
                act.body shouldNotBe null
                act.body!!.errorCode shouldBe ApiErrorResDTO.ErrorCode.UNAUTHORIZED
            }
        }
    }
})
