package walter.y.bookeye.web.interfaceAdapter.gateway.mysql.user.account.model

data class UserAccount(
        val userAccountId: Long = UNDEFINED_USER_ACCOUNT_ID,
        val principalName: String,
        val accountPassword: String,
        val isEnabled: Boolean,
        val userId: Long
) {
    companion object {
        private const val UNDEFINED_USER_ACCOUNT_ID = -1L
    }
}
