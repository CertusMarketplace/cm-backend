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
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtManager jwtManager;
    private final ForQueryingAuth forQueryingAuth;
    private final AuthenticationSuccessHandler customOAuth2LoginSuccessHandler;

    public SecurityConfig(JwtManager jwtManager, ForQueryingAuth forQueryingAuth, AuthenticationSuccessHandler customOAuth2LoginSuccessHandler) {
        this.jwtManager = jwtManager;
        this.forQueryingAuth = forQueryingAuth;
        this.customOAuth2LoginSuccessHandler = customOAuth2LoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // **AÑADIDO**
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized - Access Denied")
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login/oauth2/**", "/oauth2/**").permitAll()
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(
                                "/marketplace/**", "/css/**", "/scripts/**", "/img/**", "/video/**",
                                "/favicon.ico", "/error"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/works/**",
                                "/api/v1/ratings/**",
                                "/api/v1/people/**",
                                "/api/v1/work-categories/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/request-seller-role").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/comments").authenticated()
                        .requestMatchers("/marketplace/dashboard/seller/**").hasAuthority("Seller")
                        .requestMatchers("/marketplace/dashboard/admin/**").hasAuthority("Administrator")
                        .requestMatchers(
                                "/api/v1/users/me",
                                "/api/v1/people/me",
                                "/api/v1/paypal/**"
                        ).authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/marketplace/auth/login")
                        .successHandler(customOAuth2LoginSuccessHandler)
                )
                .addFilterBefore(new JwtAuthorizationFilterConfig(jwtManager, forQueryingAuth), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}