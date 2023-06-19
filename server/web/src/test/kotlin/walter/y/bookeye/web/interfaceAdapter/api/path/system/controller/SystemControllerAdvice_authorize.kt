@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.path.system.controller

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.useCase.system.AccessKeyValidateUseCase
import walter.y.bookeye.web.useCase.system.output.AccessKeyValidateOutput

/**
 * [SystemControllerAdvice.authorize]
 */
class SystemControllerAdvice_authorize : BehaviorSpec({
    Given("正常系") {
        val mockAccessKeyValidateUseCase = mockk<AccessKeyValidateUseCase>()
        val sut = SystemControllerAdvice(mockAccessKeyValidateUseCase)
        When("AccessKeyValidateUseCase.execute の処理が正常に終了した場合") {
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } returns AccessKeyValidateOutput
            val systemKey = "unit-test-system-access-key"
            Then("エラーが発生しないこと") {
                shouldNotThrowAny {
                    sut.authorize(systemKey)
                }
                verify(exactly = 1) {
                    mockAccessKeyValidateUseCase.execute(
                        withArg { input ->
                            input.accessKey shouldBe SystemAccessKey(systemKey)
                        }
                    )
                }
            }
        }
    }

    Given("異常系") {
        val mockAccessKeyValidateUseCase = mockk<AccessKeyValidateUseCase>()
        val sut = SystemControllerAdvice(mockAccessKeyValidateUseCase)
        When("SystemKey が NULL の場合") {
            val systemKey = null
            Then("ApiSystemAccessKeyInvalidException が発生すること") {
                shouldThrow<ApiSystemAccessKeyInvalidException> {
                    sut.authorize(systemKey = systemKey)
                }
            }
        }
        When("SystemKey が 不正な値の場合") {
            val systemKey = "abc"
            Then("ApiSystemAccessKeyInvalidException が発生すること") {
                shouldThrow<ApiSystemAccessKeyInvalidException> {
                    sut.authorize(systemKey = systemKey)
                }
            }
        }
        When("AccessValidateUseCase から SystemSettingRepositoryException.Unauthorized を受け取った場合") {
            val ex = SystemSettingRepositoryException.Unauthorized(message = "unauthorized")
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } throws ex
            val systemKey = "unit-test-system-access-key"
            Then("SystemSettingRepositoryException.Unauthorized のまま返すこと") {
                val act = shouldThrow<SystemSettingRepositoryException.Unauthorized> {
                    sut.authorize(systemKey)
                }
                act shouldBe ex
            }
        }
        When("AccessValidateUseCase から SystemSettingRepositoryException.NotFound を受け取った場合") {
            val ex = SystemSettingRepositoryException.NotFound(message = "unauthorized")
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } throws ex
            val systemKey = "unit-test-system-access-key"
            Then("SystemSettingRepositoryException.NotFound のまま返すこと") {
                val act = shouldThrow<SystemSettingRepositoryException.NotFound> {
                    sut.authorize(systemKey)
                }
                act shouldBe ex
            }
        }
        When("AccessValidateUseCase から IllegalArgumentException を受け取った場合") {
            val ex = IllegalArgumentException("illegal argument")
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } throws ex
            val systemKey = "unit-test-system-access-key"
            Then("IllegalArgumentException のまま返すこと") {
                val act = shouldThrow<IllegalArgumentException> {
                    sut.authorize(systemKey)
                }
                act shouldBe ex
            }
        }
        When("AccessValidateUseCase から IllegalStateException を受け取った場合") {
            val ex = IllegalStateException("illegal state")
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } throws ex
            val systemKey = "unit-test-system-access-key"
            Then("IllegalStateException のまま返すこと") {
                val act = shouldThrow<IllegalStateException> {
                    sut.authorize(systemKey)
                }
                act shouldBe ex
            }
        }
        When("AccessValidateUseCase から Exception を受け取った場合") {
            val ex = Exception("exception")
            every {
                mockAccessKeyValidateUseCase.execute(any())
            } throws ex
            val systemKey = "unit-test-system-access-key"
            Then("Exception のまま返すこと") {
                val act = shouldThrow<Exception> {
                    sut.authorize(systemKey)
                }
                act shouldBe ex
            }
        }
    }
})
