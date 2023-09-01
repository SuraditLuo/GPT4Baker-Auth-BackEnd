package Authentication.rest.controller;

import Authentication.rest.entity.AuthForm;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthForm loginForm) {
        String email = loginForm.getEmail();
        String password = loginForm.getPassword();

        User user = userRepository.findByEmail(email);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> response = new HashMap<>();

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            response.put("status", "401");
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.put("status", "200");
        response.put("userId", String.valueOf(user.getId()));
        response.put("email", email);
        response.put("message", "Login successful");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthForm registrationForm) {
        String email = registrationForm.getEmail();
        String password = registrationForm.getPassword();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Map<String, String> response = new HashMap<>();
        if (userRepository.existsByEmail(email)) {
            response.put("status", "409");
            response.put("message", "Email already taken");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else{
            User newUser = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();

            userRepository.save(newUser);
            response.put("status", "200");
            response.put("message", "Registration successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}