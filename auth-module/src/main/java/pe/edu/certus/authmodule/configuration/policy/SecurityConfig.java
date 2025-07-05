package pe.edu.certus.authmodule.configuration.policy;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtManager jwtManager;
    private final ForQueryingAuth forQueryingAuth;

    public SecurityConfig(JwtManager jwtManager, ForQueryingAuth forQueryingAuth) {
        this.jwtManager = jwtManager;
        this.forQueryingAuth = forQueryingAuth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No crear sesiones
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized - Acceso Denegado")
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/v1/auth/**"),
                                new AntPathRequestMatcher("/api/v1/works/**"),
                                new AntPathRequestMatcher("/api/v1/ratings/**"),
                                new AntPathRequestMatcher("/api/v1/people/**"),
                                new AntPathRequestMatcher("/api/v1/users/**")
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtAuthorizationFilterConfig(jwtManager, forQueryingAuth), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}