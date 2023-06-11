package walter.y.bookeye.web.useCase.system

import walter.y.bookeye.web.domain.system.setting.model.SystemSettingEntity
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepository
import walter.y.bookeye.web.useCase.annotation.UseCase
import walter.y.bookeye.web.useCase.system.input.AccessKeyValidateInput
import walter.y.bookeye.web.useCase.system.output.AccessKeyValidateOutput

@UseCase
class AccessKeyValidateUseCaseImpl(
    private val systemSettingRepository: SystemSettingRepository
) : AccessKeyValidateUseCase {
    override fun execute(input: AccessKeyValidateInput): AccessKeyValidateOutput {
        systemSettingRepository.authorize(SystemSettingEntity(input.accessKey))
        return AccessKeyValidateOutput
    }
}
