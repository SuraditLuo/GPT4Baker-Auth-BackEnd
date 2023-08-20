package Authentication.rest.DAO;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatChannel;
import Authentication.rest.repository.ChatChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatChannelDaoImpl implements ChatChannelDao{
    @Autowired
    ChatChannelRepository chatChannelRepository;

    @Override
    public Page<ChatChannel> getChatChannel(Pageable pageRequest) {
        return chatChannelRepository.findAll(pageRequest);
    }
    @Override
    public ChatChannel getChatChannel(Long id) {
        return chatChannelRepository.findById(id).orElse(null);
    }
    @Override
    public Optional<ChatChannel> findById(Long id) {
        return chatChannelRepository.findById(id);
    }
    @Override
    public ChatChannel save(ChatChannel user) {
        return chatChannelRepository.save(user);
    }
}
