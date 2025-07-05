package pe.edu.certus.authmodule.configuration.policy;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.edu.certus.authmodule.jwt.adapters.JwtManager;
import pe.edu.certus.authmodule.repository.entity.AuthEntity;
import pe.edu.certus.authmodule.repository.ports.driver.ForQueryingAuth;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JwtAuthorizationFilterConfig extends OncePerRequestFilter {

    private final JwtManager jwtManager;
    private final ForQueryingAuth forQueryingAuth;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthorizationFilterConfig(JwtManager jwtManager, ForQueryingAuth forQueryingAuth) {
        this.jwtManager = jwtManager;
        this.forQueryingAuth = forQueryingAuth;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Lista de rutas públicas
        List<String> excludedPaths = List.of(
                "/api/v1/auth/**",
                "/api/v1/works/**",
                "/api/v1/ratings/**",
                "/api/v1/people/**",
                "/api/v1/users/**"
        );
        return excludedPaths.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {
            JWTClaimsSet claims = jwtManager.parseJWT(token);
            String userId = claims.getSubject();

            Collection<? extends GrantedAuthority> authorities = forQueryingAuth.findById(Long.parseLong(userId))
                    .map(this::getAuthoritiesForUser)
                    .orElse(Collections.emptyList());

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userId, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        } catch ( JOSEException | ParseException | NumberFormatException e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesForUser(AuthEntity user) {
        Long roleId = user.getIdRole();
        String roleName = "USER";

        if (roleId == 1L) {
            roleName = "Administrator";
        } else if (roleId == 2L) {
            roleName = "Seller";
        } else if (roleId == 3L) {
            roleName = "Buyer";
        }

        return Stream.of(roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}