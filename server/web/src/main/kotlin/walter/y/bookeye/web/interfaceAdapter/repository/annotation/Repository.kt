package walter.y.bookeye.web.interfaceAdapter.repository.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

/**
 * [org.springframework.stereotype.Repository] の代用
 * - Spring JDBC を使用する場合、`@Repository` を付与した class が、database にアクセスすることを強制されるため
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class Repository(
    @get:AliasFor(annotation = Component::class) val value: String = ""
)
