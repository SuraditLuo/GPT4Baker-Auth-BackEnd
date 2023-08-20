package Authentication.rest.controller;

import Authentication.rest.entity.LoginForm;
import Authentication.rest.entity.RegisterForm;
import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatChannel;
import Authentication.rest.repository.ChatChannelRepository;
import Authentication.rest.service.ChatChannelService;
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
public class ChatChannelController {
    @Autowired
    ChatChannelService chatChannelService;

    @Autowired
    ChatChannelRepository chatChannelRepository;

    @GetMapping("/channels")
    ResponseEntity<?> getChatChannels() {
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getChatChannelDto(chatChannelService.getChatChannels()));
    }
    @GetMapping("channel/{id}")
    public ResponseEntity<?> getChatChannel(@PathVariable("id") Long id) {
        ChatChannel output = chatChannelService.getChatChannel(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getChatChannelDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}