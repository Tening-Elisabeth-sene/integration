package sn.edu.isepd.tic.dbe.lisa.projet.entities.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Authentification;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Role;
import sn.edu.isepd.tic.dbe.lisa.projet.service.AuthenticationService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("AuFilter");
        if (request.getHeader("Authorization") != null) {
            String authorization = request.getHeader("Authorization");
            System.out.println("Authorization: " + authorization);
            if (authorization.startsWith("Bearer ")) {
                String token = authorization.substring(7);
                System.out.println("Token: " + token);
                Optional<Authentification> authenticationRes = authenticationService.findByToken(token);
                if (authenticationRes.isPresent()) {
                    System.out.println("Authentication valide");
                    Authentification authentification = authenticationRes.get();
                    List<GrantedAuthority> roles = new  ArrayList<>();
                    for (Role r: authentification.getUser().getRoles()){
                        roles.add(new SimpleGrantedAuthority(r.getCode()));
                    }
                    if (authentification.isValide()){
                        String userName = authentification.getUser().getLogin();
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userName,null, roles));
                    }
                }
            }
//            String token = request.getHeader("Authorization").substring("Bearer ".length());
        }
        filterChain.doFilter(request, response);
    }
}
