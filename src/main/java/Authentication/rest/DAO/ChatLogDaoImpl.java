package Authentication.rest.DAO;

import Authentication.rest.entity.ChatLog;
import Authentication.rest.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ChatLogDaoImpl implements ChatLogDao{
    @Autowired
    ChatLogRepository chatLogRepository;

    @Override
    public Page<ChatLog> getChatLog(Pageable pageRequest) {
        return chatLogRepository.findAll(pageRequest);
    }

    @Override
    public Optional<ChatLog> findById(Long id) {
        return chatLogRepository.findById(id);

    }

    @Override
    public ChatLog save(ChatLog user) {
        return chatLogRepository.save(user);
    }
}
