@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.repository.user.account

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import walter.y.bookeye.web.domain.user.account.model.PrincipalName
import walter.y.bookeye.web.domain.user.account.model.UserAccountId
import walter.y.bookeye.web.domain.user.account.model.UserAccountPassword
import walter.y.bookeye.web.domain.user.model.UserId
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.mapper.UserAccountMapper
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.model.UserAccount

/**
 * [UserAccountRepositoryImpl.getOrNull]
 */
class UserAccountRepositoryImpl_getOrNull : BehaviorSpec({
    Given("正常系") {
        val mockUserAccountMapper = mockk<UserAccountMapper>()
        val sut = UserAccountRepositoryImpl(userAccountMapper = mockUserAccountMapper)
        val principalName = PrincipalName("principal.name1@test.com")

        When("UserAccountMapper.findByPrincipalName でアカウントを取得した場合") {
            val userAccount = UserAccount(
                userAccountId = 1L,
                principalName = principalName.value,
                userId = 1L,
                accountPassword = "$2a$10$" + "a".repeat(53),
                isEnabled = true
            )
            every {
                mockUserAccountMapper.findByPrincipalName(any())
            } returns userAccount

            val act = sut.getOrNull(principalName = principalName)
            Then("UserAccountEntityを返すこと") {
                act.shouldNotBeNull()
                act.userAccountId shouldBe UserAccountId(userAccount.userAccountId)
                act.userId shouldBe UserId(userAccount.userId)
                act.principalName shouldBe PrincipalName(userAccount.principalName)
                act.userAccountPassword shouldBe UserAccountPassword(userAccount.accountPassword)
                act.isEnabled() shouldBe userAccount.isEnabled

                verify(exactly = 1) {
                    mockUserAccountMapper.findByPrincipalName(principalName = principalName.value)
                }
            }
        }

        When("UserAccountMapper.findByPrincipalName でアカウントを取得しなかった場合") {
            every {
                mockUserAccountMapper.findByPrincipalName(any())
            } returns null

            val act = sut.getOrNull(principalName = principalName)
            Then("NULL を返すこと") {
                act shouldBe null
                verify(exactly = 1) {
                    mockUserAccountMapper.findByPrincipalName(principalName = principalName.value)
                }
            }
        }
    }

    // TODO: 異常系
}) {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest
}
