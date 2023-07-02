package walter.y.bookeye.web.useCase.account

import walter.y.bookeye.web.domain.user.account.repository.UserAccountRepository
import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput
import walter.y.bookeye.web.useCase.annotation.UseCase

@UseCase
class GetAccountUseCaseImpl(
    private val userAccountRepository: UserAccountRepository
) : GetAccountUseCase {
    override fun execute(input: GetAccountInput): GetAccountOutput {
        val userAccount = userAccountRepository.getOrNull(principalName = input.principalName)
        return GetAccountOutput(account = userAccount)
    }
}
