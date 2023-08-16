package Authentication.rest.config;

import Authentication.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import Authentication.rest.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        User u1, u2, u3;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u1 = userRepository.save(User.builder()
                        .password(passwordEncoder.encode("TokiDoki@081"))
                        .email("luosuradit@gmail.com")
                        .build());
        u2 = userRepository.save(User.builder()
                .password(passwordEncoder.encode("Bang093"))
                .email("thictikorne@gmail.com")
                .build());
        u3 = userRepository.save(User.builder()
                .password(passwordEncoder.encode("anuser082"))
                .email("user@outlook.com")
                .build());
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
    }
}
