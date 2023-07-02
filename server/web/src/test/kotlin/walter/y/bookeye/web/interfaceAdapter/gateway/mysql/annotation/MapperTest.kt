package walter.y.bookeye.web.interfaceAdapter.gateway.mysql.annotation

import org.springframework.context.annotation.Profile

/**
 * Mapper の Testで DB と接続する場合に使用する
 */
@Target(AnnotationTarget.CLASS)
@Profile("mapperTest")
annotation class MapperTest
