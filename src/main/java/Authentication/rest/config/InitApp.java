package Authentication.rest.config;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatLog;
import Authentication.rest.repository.ChatChannelRepository;
import Authentication.rest.repository.ChatLogRepository;
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
    @Autowired
    ChatChannelRepository chatChannelRepository;
    @Autowired
    ChatLogRepository chatLogRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        User u1, u2, u3;
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u1 = User.builder()
                .password(passwordEncoder.encode("TokiDoki@081"))
                .email("luosuradit@gmail.com")
                .build();
        u2 = User.builder()
                .password(passwordEncoder.encode("Bang093"))
                .email("thictikorne@gmail.com")
                .build();
        u3 = User.builder()
                .password(passwordEncoder.encode("anuser082"))
                .email("user@outlook.com")
                .build();
        ChatChannel ch1, ch2, ch3;
        ch1 = ChatChannel.builder()
                .name("Ting Twinky").build();
        ch2 = ChatChannel.builder()
                .name("Lala").build();
        ch3 = ChatChannel.builder()
                .name("Po").build();
        ChatLog cl1, cl2, cl3;
        cl1 = chatLogRepository.save(ChatLog.builder()
                .prompt("Hello")
                .reply("Hi").build());
        cl2 = chatLogRepository.save(ChatLog.builder()
                .prompt("How are you?")
                .reply("I'm fine, thank you!").build());
        cl3 = chatLogRepository.save(ChatLog.builder()
                .prompt("Thank you")
                .reply("You welcome!").build());
        //        Insert mock chat log into chat channel
        ch1.getChatLogs().add(cl1);
        ch2.getChatLogs().add(cl2);
        ch3.getChatLogs().add(cl3);
        cl1.setInChannel(ch1);
        cl2.setInChannel(ch2);
        cl3.setInChannel(ch3);
        //        Insert mock chat channel to each mock users.
        u1.getChatChannels().add(ch1);
        u2.getChatChannels().add(ch2);
        u3.getChatChannels().add(ch3);
        ch1.setUser(u1);
        ch2.setUser(u2);
        ch3.setUser(u3);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        chatChannelRepository.save(ch1);
        chatChannelRepository.save(ch2);
        chatChannelRepository.save(ch3);
    }
}
