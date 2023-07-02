package walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.mapper

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.model.UserAccount

//@Mapper
class UserAccountMapperImpl(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : UserAccountMapper {
    override fun findByPrincipalName(principalName: String): UserAccount? =
        namedParameterJdbcTemplate.query<UserAccount>(
            "SELECT * FROM user_account WHERE principal_name = :principalName",
            mapOf("principalName" to principalName)
        ) { rs, _ ->
            UserAccount(
                userAccountId = rs.getLong("user_account_id"),
                userId = rs.getLong("user_id"),
                principalName = rs.getString("principal_name"),
                accountPassword = rs.getString("account_password"),
                isEnabled = rs.getBoolean("is_enabled")
            )
        }.firstOrNull()

}
