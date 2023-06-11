package walter.y.bookeye.web.useCase.system.input

import walter.y.bookeye.web.domain.system.setting.model.SystemAccessKey

data class AccessKeyValidateInput(
    val accessKey: SystemAccessKey
)
