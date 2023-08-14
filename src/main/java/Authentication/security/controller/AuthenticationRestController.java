package Authentication.security.controller;


import Authentication.security.dto.UserAuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import Authentication.entity.Doctor;
import Authentication.security.dao.AuthorityDao;
import Authentication.security.dao.UserDao;
import Authentication.security.entity.Authority;
import Authentication.security.entity.JwtUser;
import Authentication.security.entity.User;
import Authentication.security.repository.AuthorityRepository;
import Authentication.security.repository.UserRepository;
import Authentication.security.service.UserService;
import Authentication.security.util.JwtTokenUtil;
import Authentication.service.DoctorService;
import Authentication.util.ProjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    AuthorityDao authorityDao;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        Map result = new HashMap();
        result.put("token", token);
        User user = userRepository.findById(((JwtUser) userDetails).getId()).orElse(null);
        if (user != null) {
            result.put("user", ProjectMapper.INSTANCE.getUserAuthDTO(user));
        }

        return ResponseEntity.ok(result);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws  AuthenticationException{
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser = authorityDao.getAuthority(01L);
        authorityRepository.save(authUser);
        User user2 = User.builder()
                .enabled(true)
                .email(user.getEmail())
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .build();

        user2.getAuthorities().add(authUser);
        authUser.getUsers().add(user2);

        userRepository.save(user2);
        userService.save(user2);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDTO(user2));
    }

//    @GetMapping("user")
//    public ResponseEntity<?> getUserLists(@RequestParam(value = "_limit", required = false) Integer perPage
//            , @RequestParam(value = "_page", required = false) Integer page) {
//        perPage = perPage == null ? 20 : perPage;
//        page = page == null ? 1 : page;
//        Page<User> pageOutput;
//        pageOutput = userService.getUsers(perPage, page);
//        HttpHeaders responseHeader = new HttpHeaders();
//
//        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
//        return new ResponseEntity<>(ProjectMapper.INSTANCE.getUserDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
//    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User output = userService.getUser(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
