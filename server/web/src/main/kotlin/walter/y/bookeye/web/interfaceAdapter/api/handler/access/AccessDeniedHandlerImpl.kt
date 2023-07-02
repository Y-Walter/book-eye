package walter.y.bookeye.web.interfaceAdapter.api.handler.access

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.logging.LogFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

class AccessDeniedHandlerImpl : AccessDeniedHandler {
    private val log = LogFactory.getLog(javaClass)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.error("[handle]", accessDeniedException)
        response.sendError(
            HttpServletResponse.SC_FORBIDDEN,
            "request is denied"
        )
    }
}
