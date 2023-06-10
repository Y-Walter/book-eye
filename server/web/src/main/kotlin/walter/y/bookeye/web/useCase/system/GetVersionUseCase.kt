package walter.y.bookeye.web.useCase.system

import walter.y.bookeye.web.useCase.system.input.GetVersionInput
import walter.y.bookeye.web.useCase.system.output.GetVersionOutput

interface GetVersionUseCase {
    fun execute(input: GetVersionInput): GetVersionOutput
}
