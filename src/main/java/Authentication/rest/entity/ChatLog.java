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
    @Column(length = 3000)
    String prompt;
    @Column(length = 3000)
    String reply;
    String pdfName;
    @ManyToOne(cascade = CascadeType.PERSIST)
    ChatChannel inChannel;
}
