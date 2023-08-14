package Authentication.security.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import Authentication.entity.Doctor;
import Authentication.entity.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    private String lastname;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    private String email;

    @Column(name = "ADDRESS", length = 100)
    @NotNull
    private String address;

    @Column(name = "AGE")
    @NotNull
    private String age;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

	@Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();
    @ElementCollection
    List<String> imageUrls;
    @OneToOne(cascade = CascadeType.REMOVE)
    Doctor doctor;
    @OneToOne(cascade = CascadeType.REFRESH)
    Patient patient;
}