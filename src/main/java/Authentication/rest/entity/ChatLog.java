package Authentication.rest.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String prompt;
    String reply;
    @ManyToOne(cascade = CascadeType.PERSIST)
    ChatChannel inChannel;
}
