package pe.edu.certus.authmodule.configuration.policy;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized - Access Denied")
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        // CORRECCIÓN: Se ha movido la regla de /api/v1/auth/** al principio y se ha hecho más explícita.
                        // Esto garantiza que todas las rutas de autenticación (login, register, google-login) sean públicas.
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Rutas públicas de la UI
                        .requestMatchers(
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
                                "/marketplace/**", "/css/**", "/scripts/**", "/img/**", "/video/**",
                                "/favicon.ico", "/error"
                        ).permitAll()

                        // Rutas públicas de la API para lectura
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/works/**",
                                "/api/v1/ratings/**",
                                "/api/v1/people/**",
                                "/api/v1/work-categories/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/users/request-seller-role")
                        .permitAll()

                        // Rutas protegidas por rol
                        .requestMatchers("/marketplace/dashboard/seller/**").hasAuthority("Seller")
                        .requestMatchers("/marketplace/dashboard/admin/**").hasAuthority("Administrator")

                        // Rutas que solo requieren estar autenticado, sin importar el rol
                        .requestMatchers(
                                "/api/v1/users/me",
                                "/api/v1/people/me",
                                "/api/v1/paypal/**"
                        ).authenticated()

=======
                                // Recursos Estáticos
                                AntPathRequestMatcher.antMatcher("/css/**"),
                                AntPathRequestMatcher.antMatcher("/scripts/**"),
                                AntPathRequestMatcher.antMatcher("/img/**"),
                                AntPathRequestMatcher.antMatcher("/video/**"),
                                AntPathRequestMatcher.antMatcher("/favicon.ico"),

=======
                                // Recursos Estáticos
                                AntPathRequestMatcher.antMatcher("/css/**"),
                                AntPathRequestMatcher.antMatcher("/scripts/**"),
                                AntPathRequestMatcher.antMatcher("/img/**"),
                                AntPathRequestMatcher.antMatcher("/video/**"),
                                AntPathRequestMatcher.antMatcher("/favicon.ico"),

>>>>>>> Stashed changes
=======
                                // Recursos Estáticos
                                AntPathRequestMatcher.antMatcher("/css/**"),
                                AntPathRequestMatcher.antMatcher("/scripts/**"),
                                AntPathRequestMatcher.antMatcher("/img/**"),
                                AntPathRequestMatcher.antMatcher("/video/**"),
                                AntPathRequestMatcher.antMatcher("/favicon.ico"),

>>>>>>> Stashed changes
                                // Rutas de UI Públicas
                                AntPathRequestMatcher.antMatcher("/"),
                                AntPathRequestMatcher.antMatcher("/home"),
                                AntPathRequestMatcher.antMatcher("/auth/**"),
                                AntPathRequestMatcher.antMatcher("/marketplace/**"),

                                // Endpoints de API Públicos
                                AntPathRequestMatcher.antMatcher("/api/v1/auth/**"),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/works/**"),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/ratings/**"),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/people/**"),
                                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/v1/users/**")
                        ).permitAll()
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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