package com.abhinav.todo.config;

import com.abhinav.todo.jwtUtils.JwtUtils;
import com.abhinav.todo.service.UserServiceForPasswordAuth;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;
    
    @Autowired
    UserServiceForPasswordAuth userServiceForPasswordAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           String authHeader = request.getHeader("Authorization");
//           System.out.println("authHeader: " +new Date()+" "+jwtUtils.extractExpiration(authHeader.substring(7))+jwtUtils.extractExpiration(authHeader.substring(7)).before(new Date()));
           String username = null;
           if (authHeader != null && authHeader.startsWith("Bearer ")) {
               username=jwtUtils.extractUsername(authHeader.substring(7));
           }
           if(username !=null){
               UserDetails userDetails=userServiceForPasswordAuth.loadUserByUsername(username);
               if(jwtUtils.extractExpiration(authHeader.substring(7)).after(new Date())){
                   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                   SecurityContextHolder.getContext().setAuthentication(authentication);
               }
           }
       } catch (Exception e) {
           response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           log.error("Error in JwtFilter",e);
           throw new RuntimeException(e);
       }
       filterChain.doFilter(request, response);
    }
}
