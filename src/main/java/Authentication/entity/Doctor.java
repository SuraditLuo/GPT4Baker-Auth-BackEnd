package Authentication.entity;

import lombok.*;
import Authentication.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    List<Patient> inCharge = new ArrayList<>();
    @OneToOne
    User user;

    @PreRemove
    private void preRemove() {
        user.setDoctor(null);
        inCharge.forEach(e -> e.setDoctor(null));
    }
}