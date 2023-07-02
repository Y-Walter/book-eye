package walter.y.bookeye.web.interfaceAdapter.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import walter.y.bookeye.web.interfaceAdapter.api.handler.access.AccessDeniedHandlerImpl
import walter.y.bookeye.web.interfaceAdapter.api.handler.authentication.AuthenticationEntryPointImpl
import walter.y.bookeye.web.interfaceAdapter.api.handler.authentication.AuthenticationFailureHandlerImpl
import walter.y.bookeye.web.interfaceAdapter.api.handler.authentication.AuthenticationSuccessHandlerImpl
import walter.y.bookeye.web.interfaceAdapter.api.handler.logout.LogoutSuccessHandlerImpl

@Configuration
@EnableWebSecurity
open class WebSecurityConfig(
    private val passwordConfig: PasswordConfig
) {
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { requests ->
            requests.requestMatchers(
                "/api/system/**",
                "/api/login"
            )
                .permitAll()
                .anyRequest()
                .authenticated()
        }

        http.formLogin { form ->
            form.loginProcessingUrl("/api/login")
                .usernameParameter("accountId")
                .passwordParameter("password")
                .successHandler(AuthenticationSuccessHandlerImpl())
                .failureHandler(AuthenticationFailureHandlerImpl())
        }

        http.exceptionHandling { exception ->
            exception.authenticationEntryPoint(AuthenticationEntryPointImpl())
                .accessDeniedHandler(AccessDeniedHandlerImpl())
        }

        http.logout { logout ->
            logout.logoutUrl("/api/logout")
                .logoutSuccessHandler(LogoutSuccessHandlerImpl())
        }

        http.csrf { csrf ->
            csrf.disable()
        }

        return http.build()
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder(passwordConfig.bcryptStrength.value)
}
