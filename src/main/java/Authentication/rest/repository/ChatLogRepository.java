package Authentication.rest.repository;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog,Long> {
    List<ChatLog> findAll();
}
