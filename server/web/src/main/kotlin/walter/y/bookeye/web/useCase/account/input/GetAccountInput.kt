package walter.y.bookeye.web.useCase.account.input

import walter.y.bookeye.web.domain.user.account.model.PrincipalName

data class GetAccountInput(
    val principalName: PrincipalName
)
