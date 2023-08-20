package Authentication.rest.service;

import Authentication.rest.DAO.ChatChannelDao;
import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatChannelServiceImpl implements ChatChannelService{
    @Autowired
    ChatChannelDao chatChannelDao;

    @Override
    public List<ChatChannel> getChatChannels() {
        return chatChannelDao.getChatChannel(Pageable.unpaged()).getContent();
    }
    @Override
    public ChatChannel getChatChannel(Long id) {
        return chatChannelDao.getChatChannel(id);
    }
}
