@file:Suppress("ClassName")

package walter.y.bookeye.web.useCase.system

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.system.version.model.SystemVersion
import walter.y.bookeye.web.domain.system.version.model.SystemVersionEntity
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepository
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepositoryException
import walter.y.bookeye.web.useCase.system.input.GetVersionInput

/**
 * [GetVersionUseCaseImpl.execute]
 */
class GetVersionUseCaseImpl_execute : BehaviorSpec({
    Given("正常系") {
        val mockSystemVersionRepository = mockk<SystemVersionRepository>()
        val sut = GetVersionUseCaseImpl(mockSystemVersionRepository)
        When("SystemVersionRepository.get が正常に終了した場合") {
            val versionValue = "1.2.3"
            every {
                mockSystemVersionRepository.get()
            } returns SystemVersionEntity(version = SystemVersion(versionValue))
            val act = sut.execute(GetVersionInput())
            Then("バージョンを取得できること") {
                act.version shouldBe SystemVersion(versionValue)
                verify(exactly = 1) {
                    mockSystemVersionRepository.get()
                }
            }
        }
    }

    Given("異常系") {
        val mockSystemVersionRepository = mockk<SystemVersionRepository>()
        val sut = GetVersionUseCaseImpl(mockSystemVersionRepository)
        When("SystemVersionRepository.get で SystemVersionRepositoryException.NotFound が発生した場合") {
            every {
                mockSystemVersionRepository.get()
            } throws SystemVersionRepositoryException.NotFound("not found")
            Then("例外をそのまま返すこと") {
                shouldThrow<SystemVersionRepositoryException.NotFound> {
                    sut.execute(GetVersionInput())
                }
            }
        }
        When("SystemVersionRepository.get で IllegalArgumentException が発生した場合") {
            every {
                mockSystemVersionRepository.get()
            } throws IllegalArgumentException("illegal argument")
            Then("例外をそのまま返すこと") {
                shouldThrow<IllegalArgumentException> {
                    sut.execute(GetVersionInput())
                }
            }
        }
        When("SystemVersionRepository.get で IllegalStateException が発生した場合") {
            every {
                mockSystemVersionRepository.get()
            } throws IllegalStateException("illegal state")
            Then("例外をそのまま返すこと") {
                shouldThrow<IllegalStateException> {
                    sut.execute(GetVersionInput())
                }
            }
        }
        When("SystemVersionRepository.get で Exception が発生した場合") {
            every {
                mockSystemVersionRepository.get()
            } throws Exception("illegal state")
            Then("例外をそのまま返すこと") {
                shouldThrow<Exception> {
                    sut.execute(GetVersionInput())
                }
            }
        }
    }
})
