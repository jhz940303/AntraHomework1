package com.spring_security.antra_spring_security.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    setFilterProcessesUrl("/authenticate");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      Map<String, String> authRequest = new ObjectMapper().readValue(request.getInputStream(), Map.class);
      String username = authRequest.get("username");
      String password = authRequest.get("password");

      System.out.println("Attempting authentication for username: " + username);

      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    UserDetails userDetails = (UserDetails) authResult.getPrincipal();

    System.out.println("User authenticated successfully: " + userDetails.getUsername());
    System.out.println("User roles: " + userDetails.getAuthorities());

    String token = jwtUtil.generateToken(userDetails);

    System.out.println("Generated JWT token: " + token);

    response.addHeader("Authorization", "Bearer " + token);

    System.out.println("Token added to the response header for user: " + userDetails.getUsername());
  }
}
