package Authentication.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatChannel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    User user;
    @OneToMany(mappedBy = "inChannel")
    @Builder.Default
    List<ChatLog> chatLogs = new ArrayList<>();
}
