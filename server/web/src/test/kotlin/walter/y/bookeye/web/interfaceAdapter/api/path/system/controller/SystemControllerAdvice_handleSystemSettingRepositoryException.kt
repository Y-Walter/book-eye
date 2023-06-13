@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.mockk
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.interfaceAdapter.api.path.model.ApiErrorResDTO

/**
 * [SystemControllerAdvice.handleSystemSettingRepositoryException]
 */
class SystemControllerAdvice_handleSystemSettingRepositoryException : BehaviorSpec({
    Given("正常系") {
        val sut = SystemControllerAdvice(mockk())
        When("SystemSettingRepositoryException.Unauthorized を検知した場合") {
            val ex = SystemSettingRepositoryException.Unauthorized("unauthorized")
            val act = sut.handleSystemSettingRepositoryException(ex)
            Then("ステータスコードが、401 になること") {
                act.statusCode shouldBe HttpStatus.UNAUTHORIZED
            }
            Then("errorCode が UNAUTHORIZED になること") {
                act.body shouldNotBe null
                act.body!!.errorCode shouldBe ApiErrorResDTO.ErrorCode.UNAUTHORIZED
            }
        }
    }

    Given("異常系") {
        val sut = SystemControllerAdvice(mockk())
        When("SystemSettingRepositoryException.NotFound を検知した場合") {
            val ex = SystemSettingRepositoryException.NotFound("notFound")
            Then("IllegalStateException を返すこと") {
                shouldThrow<IllegalStateException> {
                    sut.handleSystemSettingRepositoryException(ex)
                }
            }
        }
        When("SystemSettingRepositoryException を検知した場合") {
            val ex = SystemSettingRepositoryException(message = null, cause = null)
            Then("IllegalStateException を返すこと") {
                shouldThrow<IllegalStateException> {
                    sut.handleSystemSettingRepositoryException(ex)
                }
            }
        }
    }
})
