@file:Suppress("ClassName")

package walter.y.bookeye.web.useCase.account

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.account.repository.UserAccountRepository
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput

/**
 * [GetAccountUseCaseImpl.execute]
 */
class GetAccountUseCaseImpl_execute : BehaviorSpec({
    Given("正常系") {
        val mockUserAccountRepository = mockk<UserAccountRepository>()
        val sut = GetAccountUseCaseImpl(userAccountRepository = mockUserAccountRepository)
        When("UserAccountRepository.getOrNull で1件取得した場合") {
            val principalName = PrincipalName("principal.name@test.com")
            val userAccount = UserAccountEntity(
                userId = UserId(1L),
                userAccountId = UserAccountId(1L),
                principalName = principalName,
                userAccountPassword = UserAccountPassword("$2a$10$" + "a".repeat(53)),
                isEnabled = true
            )
            every {
                mockUserAccountRepository.getOrNull(any())
            } returns userAccount

            val act = sut.execute(GetAccountInput(principalName = principalName))
            Then("userAccountの情報を返すこと") {
                act shouldBe GetAccountOutput(account = userAccount)
                verify(exactly = 1) {
                    mockUserAccountRepository.getOrNull(principalName)
                }
            }
        }
        When("UserAccountRepository.getOrNull で NULL を受け取った場合") {
            val principalName = PrincipalName("principal.name@test.com")
            every {
                mockUserAccountRepository.getOrNull(any())
            } returns null

            val act = sut.execute(GetAccountInput(principalName = principalName))
            Then("userAccountを NULL で返すこと") {
                act shouldBe GetAccountOutput(account = null)
                verify(exactly = 1) {
                    mockUserAccountRepository.getOrNull(principalName)
                }
            }
        }
    }

    Given("異常系") {
        val mockUserAccountRepository = mockk<UserAccountRepository>()
        val sut = GetAccountUseCaseImpl(userAccountRepository = mockUserAccountRepository)
        val principalName = PrincipalName("principal.name1@test.com")
        When("UserAccountRepository.getOrNull で IllegalArgumentException が発生した場合") {
            val message = "Illegal argument"
            every {
                mockUserAccountRepository.getOrNull(any())
            } throws IllegalArgumentException(message)
            Then("例外がそのまま返ること") {
                val act = shouldThrow<IllegalArgumentException> {
                    sut.execute(GetAccountInput(principalName = principalName))
                }
                act.message shouldBe message
            }
        }
        When("UserAccountRepository.getOrNull で IllegalStateException が発生した場合") {
            val message = "Illegal state"
            every {
                mockUserAccountRepository.getOrNull(any())
            } throws IllegalStateException(message)
            Then("例外がそのまま返ること") {
                val act = shouldThrow<IllegalStateException> {
                    sut.execute(GetAccountInput(principalName = principalName))
                }
                act.message shouldBe message
            }
        }
        When("UserAccountRepository.getOrNull で Exception が発生した場合") {
            val message = "exception"
            every {
                mockUserAccountRepository.getOrNull(any())
            } throws Exception(message)
            Then("例外がそのまま返ること") {
                val act = shouldThrow<Exception> {
                    sut.execute(GetAccountInput(principalName = principalName))
                }
                act.message shouldBe message
            }
        }
    }
}) {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest
}
