package walter.y.bookeye.web.useCase.system

import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepository
import walter.y.bookeye.web.useCase.annotation.UseCase
import walter.y.bookeye.web.useCase.system.input.GetVersionInput
import walter.y.bookeye.web.useCase.system.output.GetVersionOutput

@UseCase
class GetVersionUseCaseImpl(
    private val systemVersionRepository: SystemVersionRepository
) : GetVersionUseCase {
    override fun execute(input: GetVersionInput): GetVersionOutput {
        val systemVersionEntity = systemVersionRepository.get()
        return GetVersionOutput(version = systemVersionEntity.version)
    }
}
