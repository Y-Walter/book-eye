package walter.y.bookeye.web.domain.system.setting.repository

import walter.y.bookeye.web.domain.system.setting.model.SystemSettingEntity

interface SystemSettingRepository {
    /**
     * @exception SystemSettingRepositoryException.Unauthorized
     * @exception SystemSettingRepositoryException.NotFound
     */
    fun authorize(entity: SystemSettingEntity)
}
