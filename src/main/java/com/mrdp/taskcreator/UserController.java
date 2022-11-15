package com.mrdp.taskcreator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mrdp.taskcreator.user.dto.UserDto;
import com.mrdp.taskcreator.user.dao.UserEntity;
import com.mrdp.taskcreator.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserLogin userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        List<String> authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 100000 * 60 * 1000))
                .withIssuer("x")
                .withClaim("roles", authorities)
                .sign(algorithm);

        return ResponseEntity.ok(new AuthResponse(accessToken, authorities.toArray(new String[0])));
    }

//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") String id){
//        userService.deleteUser(id);
//        return ResponseEntity.noContent();
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UserLogin{
        private String username;
        private String password;
    }

    @Data
    @AllArgsConstructor
    private static class AuthResponse{
        private String token;
        private Object[] roles;
    }
}
