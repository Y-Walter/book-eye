package walter.y.bookeye.web.interfaceAdapter.api.handler.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class AuthenticationSuccessHandlerImpl : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
//        response.addCookie(Cookie("", ""))
        response.status = HttpServletResponse.SC_OK
    }
}
