package walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.mapper

import walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.model.UserAccount

interface UserAccountMapper {
    fun findByPrincipalName(principalName: String): UserAccount?
}
