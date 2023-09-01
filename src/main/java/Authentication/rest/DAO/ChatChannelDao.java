package Authentication.rest.DAO;

import Authentication.rest.entity.ChatChannel;
import Authentication.rest.entity.ChatChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChatChannelDao {
    ChatChannel getChatChannel(Long id);
}
