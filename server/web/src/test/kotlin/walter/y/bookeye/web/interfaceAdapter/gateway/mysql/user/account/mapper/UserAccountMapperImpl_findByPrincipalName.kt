@file:Suppress("ClassName")

package walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.mapper

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.annotation.MapperTest
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.model.UserAccount

/**
 * [UserAccountMapperImpl.findByPrincipalName]
 */
@MapperTest
class UserAccountMapperImpl_findByPrincipalName : BehaviorSpec({
    Given("正常系") {
        val mockNamedParameterJdbcTemplate = mockk<NamedParameterJdbcTemplate>()
        val sut = UserAccountMapperImpl(namedParameterJdbcTemplate = mockNamedParameterJdbcTemplate)

        When("条件に一致するレコードを1件取得した場合") {
            val principalName = "principal.name1@test.com"
            val userAccount = UserAccount(
                userAccountId = 1L,
                userId = 1L,
                principalName = principalName,
                accountPassword = "$2a$10$" + "a".repeat(53),
                isEnabled = true
            )
            every {
                mockNamedParameterJdbcTemplate.query(any(), any<Map<String, Any?>>(), any<RowMapper<UserAccount>>())
            } returns mutableListOf(userAccount)

            val act = sut.findByPrincipalName(principalName = principalName)

            Then("UserAccount が返ること") {
                act.shouldNotBeNull()
                act shouldBe userAccount
            }
        }

        When("条件に一致するレコードを0件取得した場合") {
            val principalName = "principal.name1@test.com"
            every {
                mockNamedParameterJdbcTemplate.query(any(), any<Map<String, Any?>>(), any<RowMapper<UserAccount>>())
            } returns mutableListOf()

            val act = sut.findByPrincipalName(principalName = principalName)

            Then("NULL が返ること") {
                act shouldBe null
            }
        }
    }
}) {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest
}
