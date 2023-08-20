package Authentication.rest.repository;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatChannelRepository extends JpaRepository<ChatChannel,Long> {
    List<ChatChannel> findAll();
}
