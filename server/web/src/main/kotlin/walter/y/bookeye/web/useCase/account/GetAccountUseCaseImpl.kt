package walter.y.bookeye.web.useCase.account

import walter.y.bookeye.web.domain.user.account.repository.UserAccountRepository
import walter.y.bookeye.web.useCase.account.input.GetAccountInput
import walter.y.bookeye.web.useCase.account.output.GetAccountOutput

//@UseCase
class GetAccountUseCaseImpl(
    private val userAccountRepository: UserAccountRepository
) : GetAccountUseCase {
    override fun execute(input: GetAccountInput): GetAccountOutput {
        val userAccountOrNull = userAccountRepository.getOrNull(input.principalName)
        return GetAccountOutput(account = userAccountOrNull)
    }
}
