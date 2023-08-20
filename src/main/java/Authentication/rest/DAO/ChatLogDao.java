package Authentication.rest.DAO;

import Authentication.rest.entity.ChatLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChatLogDao {
    Page<ChatLog> getChatLog(Pageable pageRequest);
    Optional<ChatLog> findById(Long id);
    ChatLog save(ChatLog chatLog);
}
