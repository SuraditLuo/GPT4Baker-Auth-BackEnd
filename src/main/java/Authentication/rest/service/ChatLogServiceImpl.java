package Authentication.rest.service;

import Authentication.rest.DAO.ChatLogDao;
import Authentication.rest.entity.ChatLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLogServiceImpl implements ChatLogService{
    @Autowired
    ChatLogDao chatLogDao;

    @Override
    public List<ChatLog> getChatLogs() {
        return chatLogDao.getChatLog(Pageable.unpaged()).getContent();
    }
}
