package naderdeghaili.u5w3d2hw.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import naderdeghaili.u5w3d2hw.entities.Dipendente;
import naderdeghaili.u5w3d2hw.exceptions.UnauthorizedException;
import naderdeghaili.u5w3d2hw.services.DipendentiService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final DipendentiService dipendentiService;

    public JWTCheckerFilter(JWTTools jwtTools, DipendentiService dipendentiService) {
        this.jwtTools = jwtTools;
        this.dipendentiService = dipendentiService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(request.getMethod());


        // AUTENTICAZIONE
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("token sbagliato");

        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        //AUTORIZZAZIONE
        UUID dipendenteId = jwtTools.getIdFromToken(accessToken);
        Dipendente authDipendente = this.dipendentiService.findById(dipendenteId);


        Authentication authentication = new UsernamePasswordAuthenticationToken(authDipendente, null, authDipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
