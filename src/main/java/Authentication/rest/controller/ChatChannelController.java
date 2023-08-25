package Authentication.rest.controller;

import Authentication.rest.DTO.ChatChannelDto;
import Authentication.rest.DTO.UserDto;
import Authentication.rest.entity.*;
import Authentication.rest.entity.ChatChannel;
import Authentication.rest.repository.ChatChannelRepository;
import Authentication.rest.repository.ChatLogRepository;
import Authentication.rest.repository.UserRepository;
import Authentication.rest.service.ChatChannelService;
import Authentication.rest.service.UserService;
import Authentication.rest.util.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ChatChannelController {
    @Autowired
    ChatChannelService chatChannelService;

    @Autowired
    ChatChannelRepository chatChannelRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChatLogRepository chatLogRepository;

    @Autowired
    UserService userService;
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
    @PostMapping("/create/{userId}")
    public ResponseEntity<UserDto> createChatChannel(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a new chat channel
        ChatChannel newChatChannel = new ChatChannel();
        newChatChannel.setName("New chat");
        newChatChannel.setUser(user);
        chatChannelRepository.save(newChatChannel);

        // Add the new chat channel to the user's chat channels list
        user.getChatChannels().add(newChatChannel);
        userRepository.save(user);

        User output = userService.getUser(userId);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getUserDto(output));
    }

    @PostMapping("/delete/{channelId}")
    public ResponseEntity<UserDto> deleteChatChannel(@PathVariable Long channelId) {
        ChatChannel chatChannel = chatChannelRepository.findById(channelId)
                .orElseThrow(() -> new IllegalArgumentException("Chat channel not found"));
        // Get the user associated with the chat channel
        User user = chatChannel.getUser();

        user.getChatChannels().remove(chatChannel);
        userRepository.save(user);
        chatChannelRepository.delete(chatChannel);
        UserDto userDto = ProjectMapper.INSTANCE.getUserDto(user);

        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/edit_channel/{id}")
    public ResponseEntity<ChatChannelDto> editChatChannelName(
            @PathVariable Long id,
            @RequestParam String name
    ) {
        ChatChannel chatChannel = chatChannelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat channel not found"));

        chatChannel.setName(name);
        chatChannelRepository.save(chatChannel);

        ChatChannelDto chatChannelDto = ProjectMapper.INSTANCE.getChatChannelDto(chatChannel);

        return ResponseEntity.ok(chatChannelDto);
    }
    @PostMapping("/createLog/{id}")
    public ResponseEntity<ChatChannelDto> createLog(
            @PathVariable Long id,
            @RequestParam String prompt,
            @RequestParam String reply,
            @RequestParam String pdfName
    ) {
        ChatChannel chatChannel = chatChannelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chat channel not found"));
        ChatLog log = new ChatLog();
        log.setPrompt(prompt);
        log.setReply(reply);
        log.setPdfName(pdfName);
        log.setInChannel(chatChannel);
        chatChannel.getChatLogs().add(log);
        chatLogRepository.save(log);
        chatChannelRepository.save(chatChannel);
        ChatChannel output = chatChannelService.getChatChannel(id);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getChatChannelDto(output));
    }
}