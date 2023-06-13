@file:Suppress("ClassName")

package walter.y.bookeye.web.useCase.system

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepository
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.useCase.system.input.AccessKeyValidateInput

/**
 * [AccessKeyValidateUseCase.execute]
 */
class AccessKeyValidateUseCaseImpl_execute : BehaviorSpec({
    Given("正常系") {
        val mockSystemSettingRepository = mockk<SystemSettingRepository>()
        val sut = AccessKeyValidateUseCaseImpl(mockSystemSettingRepository)
        When("SystemSettingRepository.authorize が正常に終了した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } returns Unit
            val accessKeyValue = "unit-test-system-access-key"
            val input = AccessKeyValidateInput(accessKey = SystemAccessKey(accessKeyValue))
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    sut.execute(input)
                }
                verify(exactly = 1) {
                    mockSystemSettingRepository.authorize(
                        withArg { entity ->
                            entity.systemAccessKey shouldBe SystemAccessKey(accessKeyValue)
                        }
                    )
                }
            }
        }
    }

    Given("異常系") {
        val mockSystemSettingRepository = mockk<SystemSettingRepository>()
        val sut = AccessKeyValidateUseCaseImpl(mockSystemSettingRepository)
        When("SystemSettingRepository.authorize で SystemSettingRepositoryException.Unauthorized が発生した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } throws SystemSettingRepositoryException.Unauthorized("unauthorized")
            val accessKey = SystemAccessKey("unit-test-system-access-key")
            Then("例外がそのまま返ること") {
                shouldThrow<SystemSettingRepositoryException.Unauthorized> {
                    sut.execute(AccessKeyValidateInput(accessKey = accessKey))
                }
            }
        }
        When("SystemSettingRepository.authorize で SystemSettingRepositoryException.NotFound が発生した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } throws SystemSettingRepositoryException.NotFound("not found")
            val accessKey = SystemAccessKey("unit-test-system-access-key")
            Then("例外がそのまま返ること") {
                shouldThrow<SystemSettingRepositoryException.NotFound> {
                    sut.execute(AccessKeyValidateInput(accessKey = accessKey))
                }
            }
        }
        When("SystemSettingRepository.authorize で IllegalArgumentException が発生した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } throws IllegalArgumentException("illegal argument")
            val accessKey = SystemAccessKey("unit-test-system-access-key")
            Then("例外がそのまま返ること") {
                shouldThrow<IllegalArgumentException> {
                    sut.execute(AccessKeyValidateInput(accessKey = accessKey))
                }
            }
        }
        When("SystemSettingRepository.authorize で IllegalStateException が発生した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } throws IllegalStateException("illegal state")
            val accessKey = SystemAccessKey("unit-test-system-access-key")
            Then("例外がそのまま返ること") {
                shouldThrow<IllegalStateException> {
                    sut.execute(AccessKeyValidateInput(accessKey = accessKey))
                }
            }
        }
        When("SystemSettingRepository.authorize で Exception が発生した場合") {
            every {
                mockSystemSettingRepository.authorize(any())
            } throws Exception("exception")
            val accessKey = SystemAccessKey("unit-test-system-access-key")
            Then("例外がそのまま返ること") {
                shouldThrow<Exception> {
                    sut.execute(AccessKeyValidateInput(accessKey = accessKey))
                }
            }
        }
    }
})
