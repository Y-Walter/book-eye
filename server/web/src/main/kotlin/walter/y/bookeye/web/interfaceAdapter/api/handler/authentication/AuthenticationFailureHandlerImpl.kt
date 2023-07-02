package walter.y.bookeye.web.interfaceAdapter.api.handler.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

class AuthenticationFailureHandlerImpl : AuthenticationFailureHandler {
    private val log = LogFactory.getLog(javaClass)
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        log.error("[onAuthenticationFailure]", exception)
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "loginId / password is invalid"
        )
    }
}
