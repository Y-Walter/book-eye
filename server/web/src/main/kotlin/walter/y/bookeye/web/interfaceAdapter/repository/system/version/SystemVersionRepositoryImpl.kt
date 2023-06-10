package walter.y.bookeye.web.interfaceAdapter.repository.system.version

import org.springframework.stereotype.Repository
import walter.y.bookeye.web.domain.system.setting.repository.SystemSettingRepositoryException
import walter.y.bookeye.web.domain.system.version.model.SystemVersionEntity
import walter.y.bookeye.web.domain.system.version.repository.SystemVersionRepository
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client.SystemVersionClient
import walter.y.bookeye.web.interfaceAdapter.gateway.env.system.version.client.SystemVersionClientException

@Repository
class SystemVersionRepositoryImpl(
    private val systemVersionClient: SystemVersionClient
) : SystemVersionRepository {
    override fun get(): SystemVersionEntity {
        val version = try {
            systemVersionClient.getVersion()
        } catch (ex: SystemVersionClientException.NotFound) {
            throw SystemSettingRepositoryException.NotFound(cause = ex)
        }
        return SystemVersionEntity(version = version)
    }
}
