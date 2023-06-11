package walter.y.bookeye.web.domain.system.version.repository

import walter.y.bookeye.web.domain.system.version.model.SystemVersionEntity

interface SystemVersionRepository {
    /**
     * @exception SystemVersionRepositoryException.NotFound
     */
    fun get(): SystemVersionEntity
}
