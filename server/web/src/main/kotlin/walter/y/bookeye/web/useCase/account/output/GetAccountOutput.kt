package walter.y.bookeye.web.useCase.account.output

import walter.y.bookeye.web.domain.user.account.model.UserAccountEntity

data class GetAccountOutput(
    val account: UserAccountEntity?
)
