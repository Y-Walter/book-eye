package walter.y.bookeye.web.interfaceAdapter.api.handler.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class AuthenticationEntryPointImpl : AuthenticationEntryPoint {
    private val log = LogFactory.getLog(javaClass)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.error("[commence]", authException)
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "request is unauthorized"
        )
    }
}
