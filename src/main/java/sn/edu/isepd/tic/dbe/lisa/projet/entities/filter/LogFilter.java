package sn.edu.isepd.tic.dbe.lisa.projet.entities.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("###### LogFilter");
        String adresseIp = request.getRemoteAddr();
        String path = request.getRequestURI();
        String formatDemnder = request.getHeader("Accept");
        System.out.println(" Adresse: " + adresseIp);
        System.out.println(" Path: " + path);
        System.out.println(" Format de Demnder: " + formatDemnder);

//        if (!"application/json".equalsIgnoreCase(formatDemnder)) {
//            response.sendError(400, "Format de Demnder (json) incorrecte");
//            return;
//        }

        filterChain.doFilter(request, response);

    }
}
