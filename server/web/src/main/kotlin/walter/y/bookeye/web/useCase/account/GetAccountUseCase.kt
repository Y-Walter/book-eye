package walter.y.bookeye.web.useCase.account

import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput

interface GetAccountUseCase {
    fun execute(input: GetAccountInput): GetAccountOutput
}
