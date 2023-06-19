@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepositoryException

/**
 * [SystemControllerAdvice.handleSystemVersionRepositoryException]
 */
class SystemControllerAdvice_handleSystemVersionRepositoryException : BehaviorSpec({
    Given("異常系") {
        val sut = SystemControllerAdvice(mockk())
        When("SystemVersionRepositoryException.NotFound を検知した場合") {
            val ex = SystemVersionRepositoryException.NotFound("not found")
            Then("IllegalStateException を返すこと") {
                shouldThrow<IllegalStateException> {
                    sut.handleSystemVersionRepositoryException(ex)
                }
            }
        }
        When("SystemVersionRepositoryException を検知した場合") {
            val ex = SystemVersionRepositoryException(message = null, cause = null)
            Then("IllegalStateException を返すこと") {
                shouldThrow<IllegalStateException> {
                    sut.handleSystemVersionRepositoryException(ex)
                }
            }
        }
    }
})
