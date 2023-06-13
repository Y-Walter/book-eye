@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus
import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepositoryException
import walter.y.bookeye.web.interfaceAdapter.api.path.system.model.VersionResDTO
import walter.y.bookeye.web.useCase.system.GetVersionUseCase
import walter.y.bookeye.web.useCase.system.output.GetVersionOutput

/**
 * [SystemController.getVersion]
 */
class SystemController_getVersion : BehaviorSpec({
    Given("正常系") {
        val mockGetVersionUseCase = mockk<GetVersionUseCase>()
        val sut = SystemController(
            getVersionUseCase = mockGetVersionUseCase
        )
        When("GetVersionUseCase が正常に終了した場合") {
            val version = SystemVersion("1.2.3")
            every {
                mockGetVersionUseCase.execute(any())
            } returns GetVersionOutput(version = version)
            val act = sut.getVersion()

            Then("ステータスコードが 200 になること") {
                act.statusCode shouldBe HttpStatus.OK
            }
            Then("バージョンを取得できること") {
                act.body shouldBe VersionResDTO(version = version.value)
                verify(exactly = 1) {
                    mockGetVersionUseCase.execute(any())
                }
            }
        }
    }

    Given("異常系") {
        val mockGetVersionUseCase = mockk<GetVersionUseCase>()
        val sut = SystemController(
            getVersionUseCase = mockGetVersionUseCase
        )
        When("SystemVersionRepositoryException を受け取った場合") {
            val ex = SystemVersionRepositoryException(message = "exception", cause = null)
            every {
                mockGetVersionUseCase.execute(any())
            } throws ex
            val act = shouldThrow<SystemVersionRepositoryException> {
                sut.getVersion()
            }
            Then("SystemVersionRepositoryException をそのまま返すこと") {
                act shouldBe ex
            }
        }
        When("IllegalArgumentException を受け取った場合") {
            val ex = IllegalArgumentException("exception")
            every {
                mockGetVersionUseCase.execute(any())
            } throws ex
            val act = shouldThrow<IllegalArgumentException> {
                sut.getVersion()
            }
            Then("IllegalArgumentException をそのまま返すこと") {
                act shouldBe ex
            }
        }
        When("IllegalStateException を受け取った場合") {
            val ex = IllegalStateException("exception")
            every {
                mockGetVersionUseCase.execute(any())
            } throws ex
            val act = shouldThrow<IllegalStateException> {
                sut.getVersion()
            }
            Then("IllegalStateException をそのまま返すこと") {
                act shouldBe ex
            }
        }
        When("Exception を受け取った場合") {
            val ex = Exception("exception")
            every {
                mockGetVersionUseCase.execute(any())
            } throws ex
            val act = shouldThrow<Exception> {
                sut.getVersion()
            }
            Then("Exception をそのまま返すこと") {
                act shouldBe ex
            }
        }
    }
})
