package com.spring_security.antra_spring_security.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

  @GetMapping("/user")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public String userOnly() {
    return "this is a test for users";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public String adminOnly() {
    return "this is a test for admins";
  }
}
