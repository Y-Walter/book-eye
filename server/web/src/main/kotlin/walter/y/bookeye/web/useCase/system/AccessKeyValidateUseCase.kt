package walter.y.bookeye.web.useCase.system

import walter.y.bookeye.web.useCase.system.input.AccessKeyValidateInput
import walter.y.bookeye.web.useCase.system.output.AccessKeyValidateOutput

interface AccessKeyValidateUseCase {
    fun execute(input: AccessKeyValidateInput): AccessKeyValidateOutput
}
