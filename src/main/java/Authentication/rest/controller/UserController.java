package Authentication.rest.controller;

import Authentication.rest.entity.LoginForm;
import Authentication.rest.entity.RegisterForm;
import Authentication.rest.entity.User;
import Authentication.rest.repository.UserRepository;
import Authentication.rest.service.UserService;
import Authentication.rest.util.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(userService.getUsers()));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginForm loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        User user = userRepository.findByUsername(username);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> response = new HashMap<>();

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            response.put("status", "401");
            response.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        response.put("status", "200");
        response.put("username", username);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterForm registrationForm) {
        String username = registrationForm.getUsername();
        String password = registrationForm.getPassword();
        String email = registrationForm.getEmail();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> response = new HashMap<>();
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            response.put("status", "409");
            response.put("message", "Username or email already taken");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else{
            User newUser = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .build();

            userRepository.save(newUser);
            response.put("status", "200");
            response.put("message", "Registration successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
}