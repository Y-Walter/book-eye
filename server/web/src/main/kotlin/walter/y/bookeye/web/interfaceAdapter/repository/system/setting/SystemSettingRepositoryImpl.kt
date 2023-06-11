package walter.y.bookeye.web.interfaceAdapter.repository.system.setting

import org.springframework.stereotype.Repository
import walter.y.bookeye.web.domain.system.setting.model.SystemSettingEntity
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepository
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client.SystemSettingClient
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.setting.client.SystemSettingClientException

@Repository
class SystemSettingRepositoryImpl(
    private val systemSettingClient: SystemSettingClient
) : SystemSettingRepository {
    override fun authorize(entity: SystemSettingEntity) {
        try {
            systemSettingClient.authorize(entity.systemAccessKey)
        } catch (ex: SystemSettingClientException.Unauthorized) {
            throw SystemSettingRepositoryException.Unauthorized(cause = ex)
        } catch (ex: SystemSettingClientException.NotFound) {
            throw SystemSettingRepositoryException.NotFound(cause = ex)
        }
    }
}
