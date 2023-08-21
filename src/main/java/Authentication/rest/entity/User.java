package Authentication.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String email;
    String password;
    @OneToMany(mappedBy = "user")
    @Builder.Default
    List<ChatChannel> chatChannels = new ArrayList<>();
}
