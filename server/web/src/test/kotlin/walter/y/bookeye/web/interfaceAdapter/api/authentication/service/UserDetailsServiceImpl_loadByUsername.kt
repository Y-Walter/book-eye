@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.api.authentication.service

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
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.interfaceAdapter.api.authentication.model.UserPrincipal
import walter.y.bookeye.web.useCase.account.GetAccountUseCase
import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput

/**
 * [UserDetailsServiceImpl.loadUserByUsername]
 */
class UserDetailsServiceImpl_loadByUsername : BehaviorSpec({
    Given("正常系") {
        val mockGetAccountUseCase = mockk<GetAccountUseCase>()
        val sut = UserDetailsServiceImpl(getAccountUseCase = mockGetAccountUseCase)
        val username = "principal.name1@test.com"

        When("GetAccountUseCase.execute でアカウントを取得できた場合") {
            val userAccount = UserAccountEntity(
                userId = UserId(1L),
                userAccountId = UserAccountId(1L),
                principalName = PrincipalName(username),
                userAccountPassword = UserAccountPassword("$2a$10$" + "a".repeat(53)),
                isEnabled = true
            )
            every {
                mockGetAccountUseCase.execute(any())
            } returns GetAccountOutput(account = userAccount)

            val act = sut.loadUserByUsername(username = username)

            Then("UserPrincipal が返ること") {
                act shouldBe UserPrincipal(
                    userId = userAccount.userId,
                    accountId = userAccount.userAccountId,
                    principalName = userAccount.principalName,
                    password = userAccount.userAccountPassword,
                    accountExpired = false,
                    accountLocked = userAccount.isLocked(),
                    accountEnabled = userAccount.isEnabled(),
                    credentialExpired = false
                )
                verify(exactly = 1) {
                    mockGetAccountUseCase.execute(GetAccountInput(principalName = PrincipalName(username)))
                }
            }
        }
        When("GetAccountUseCase.execute でアカウントを取得できなかった場合") {
            every {
                mockGetAccountUseCase.execute(any())
            } returns GetAccountOutput(account = null)

            val act = sut.loadUserByUsername(username = username)

            Then("NULL が返ること") {
                act shouldBe null
                verify(exactly = 1) {
                    mockGetAccountUseCase.execute(GetAccountInput(principalName = PrincipalName(username)))
                }
            }
        }
    }

    Given("異常系") {
        val mockGetAccountUseCase = mockk<GetAccountUseCase>()
        val sut = UserDetailsServiceImpl(getAccountUseCase = mockGetAccountUseCase)
        val username = "principal.name1@test.com"

        When("GetAccountUseCase.execute で IllegalArgumentException が発生した場合") {
            val message = "illegal argument"
            every {
                mockGetAccountUseCase.execute(any())
            } throws IllegalArgumentException(message)

            Then("例外がそのまま返ること") {
                val act = shouldThrow<IllegalArgumentException> {
                    sut.loadUserByUsername(username)
                }
                act.message shouldBe message
            }
        }
        When("GetAccountUseCase.execute で IllegalStateException が発生した場合") {
            val message = "illegal state"
            every {
                mockGetAccountUseCase.execute(any())
            } throws IllegalStateException(message)

            Then("例外がそのまま返ること") {
                val act = shouldThrow<IllegalStateException> {
                    sut.loadUserByUsername(username)
                }
                act.message shouldBe message
            }
        }
        When("GetAccountUseCase.execute で Exception が発生した場合") {
            val message = "exception"
            every {
                mockGetAccountUseCase.execute(any())
            } throws Exception(message)

            Then("例外がそのまま返ること") {
                val act = shouldThrow<Exception> {
                    sut.loadUserByUsername(username)
                }
                act.message shouldBe message
            }
        }
    }
}) {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest
}
