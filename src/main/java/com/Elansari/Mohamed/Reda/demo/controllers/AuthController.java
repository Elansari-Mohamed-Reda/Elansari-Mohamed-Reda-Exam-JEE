package com.Elansari.Mohamed.Reda.demo.controllers;

import com.Elansari.Mohamed.Reda.demo.dtos.LoginRequest;
import com.Elansari.Mohamed.Reda.demo.dtos.JwtResponse;
import com.Elansari.Mohamed.Reda.demo.security.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "API pour l'authentification des utilisateurs")
public class AuthController {
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtils jwtUtils;

    @PostMapping("/login")
    @Operation(summary = "Authentifier un utilisateur", description = "Retourne un token JWT si les identifiants sont valides")
    @ApiResponse(responseCode = "200", description = "Authentification réussie", 
                 content = @Content(schema = @Schema(implementation = JwtResponse.class)))
    @ApiResponse(responseCode = "401", description = "Identifiants invalides")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
