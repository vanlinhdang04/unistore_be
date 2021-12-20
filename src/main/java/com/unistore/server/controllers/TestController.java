package com.unistore.server.controllers;

import com.unistore.server.common.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(@RequestHeader String Authorization) {
        String token = Authorization.substring(7, Authorization.length());
        String username = jwtUtils.getUsernameFromJwtToken(token);
        Boolean check = jwtUtils.validateJwtToken(token);
        return username + " " + check;
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess(@RequestHeader String token) {
        //jwtUtils.getUsernameFromJwtToken()
        return token;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
