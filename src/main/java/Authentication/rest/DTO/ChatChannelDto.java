package Authentication.rest.DTO;

import Authentication.rest.entity.ChatLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannelDto {
    Long id;
    String name;
    List<ChannelLogDto> chatLogs = new ArrayList<>();
}
