package Authentication.rest.service;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatChannel;

import java.util.List;

public interface ChatChannelService {
    List<ChatChannel> getChatChannels();
    ChatChannel getChatChannel(Long id);
}
