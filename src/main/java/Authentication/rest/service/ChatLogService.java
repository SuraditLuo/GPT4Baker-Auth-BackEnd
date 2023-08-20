package Authentication.rest.service;

import Authentication.rest.entity.ChatLog;

import java.util.List;

public interface ChatLogService {
    List<ChatLog> getChatLogs();
}
