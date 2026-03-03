package org.example.EventPulseApplication.controller;
import lombok.RequiredArgsConstructor;
import org.example.EventPulseApplication.dto.JwtResponse;
import org.example.EventPulseApplication.dto.LoginRequest;
import org.example.EventPulseApplication.entity.User;
import org.example.EventPulseApplication.jwt.JwtUtils;
import org.example.EventPulseApplication.repository.UserRepository;
import org.example.EventPulseApplication.security.SecurityConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationManager {
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUse(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));;
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assert userDetails != null; // проверяем, не равен ли он, на null
        String role = userDetails.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_USER");
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), role));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Ошибка: Пользователь уже существует!");
        }
        signUpRequest.setPassword(securityConfiguration.bCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        userRepository.save(signUpRequest);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");
    }
}