package Authentication.config;

import Authentication.entity.Patient;
import Authentication.repository.PatientRepository;
import Authentication.security.entity.Authority;
import Authentication.security.entity.AuthorityName;
import Authentication.security.entity.User;
import Authentication.security.repository.AuthorityRepository;
import Authentication.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class initApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Patient tempPatient = null;
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user13.getFirstname())
                .lastname(user13.getLastname())
                .age(user13.getAge())
                .address(user13.getAddress())
                .status("already get second doses")
                .build());
        tempPatient.setUser(user13);
        user13.setPatient(tempPatient);

    }
    User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15, user16, user17;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser =
                Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin =
                Authority.builder().name(AuthorityName.ROLE_ADMIN).build();

        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .age("50")
                .address("Chiang Mai")
                .enabled(true)
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .age("28")
                .address("Chiang Mai")
                .enabled(true)
                .build();

        user3 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctor")
                .lastname("doctor")
                .email("doctor@user.com")
                .age("49")
                .address("Chiang Mai")
                .enabled(true)
                .build();
        user4 = User.builder()
                .username("patient")
                .password(encoder.encode("patient"))
                .firstname("patient")
                .lastname("patient")
                .email("patient@user.com")
                .age("40")
                .address("Chiang Rai")
                .enabled(true)
                .build();
        user5 = User.builder()
                .username("BJ_1nwza")
                .password(encoder.encode("123"))
                .firstname("Black")
                .lastname("Jack")
                .email("BJ@mac.com")
                .age("30")
                .address("6087 Elgar Terrace")
                .enabled(true)
                .build();
        user6 = User.builder()
                .username("jcastellb")
                .password(encoder.encode("123"))
                .firstname("Joye")
                .lastname("Castell")
                .email("jcastellb@skype.com")
                .age("14")
                .address("0 Spaight Circle")
                .enabled(true)
                .build();
        user7 = User.builder()
                .username("pcotterellw")
                .password(encoder.encode("123"))
                .firstname("Putnem")
                .lastname("Cotterell")
                .email("pcotterellw@symantec.com")
                .age("45")
                .address("7 Scofield Park")
                .enabled(true)
                .build();
        user8 = User.builder()
                .username("nmaclucais22")
                .password(encoder.encode("123"))
                .firstname("Ninnette")
                .lastname("MacLucais")
                .email("nmaclucais22@icio.us")
                .age("8")
                .address("432 Dottie Alley")
                .enabled(true)
                .build();
        user9 = User.builder()
                .username("aboost2g")
                .password(encoder.encode("123"))
                .firstname("Ame")
                .lastname("Boost")
                .email("aboost2g@hibu.com")
                .age("25")
                .address("35 Marquette Center")
                .enabled(true)
                .build();
        user10 = User.builder()
                .username("bro")
                .password(encoder.encode("123"))
                .firstname("Ban")
                .lastname("Grosier")
                .email("bgrosier2w@wikispaces.com")
                .age("20")
                .address("6 Golf Course Terrace")
                .enabled(true)
                .build();
        user11 = User.builder()
                .username("sondrak4a")
                .password(encoder.encode("123"))
                .firstname("Stephie")
                .lastname("Ondrak")
                .email("sondrak4a@ocn.ne.jp")
                .age("26")
                .address("30 Mockingbird Junction")
                .enabled(true)
                .build();
        user12 = User.builder()
                .username("bpinnick5e")
                .password(encoder.encode("123"))
                .firstname("Barron")
                .lastname("Pinnick")
                .email("bpinnick5e@discuz.net")
                .age("11")
                .address("487 Sunnyside Court")
                .enabled(true)
                .build();
        user13 = User.builder()
                .username("TokiDokor")
                .password(encoder.encode("TokiDokor"))
                .firstname("Suradit")
                .lastname("Luo")
                .email("gialeng@outlook.com")
                .age("20")
                .address("Chiang Rai")
                .enabled(true)
                .build();
        user14 = User.builder()
                .username("A1iBa")
                .password(encoder.encode("A1iBa"))
                .firstname("Jack")
                .lastname("Ma")
                .email("ali@baba.com")
                .age("56")
                .address("Beijing")
                .enabled(true)
                .build();
        user15 = User.builder()
                .username("BatDad")
                .password(encoder.encode("BatDad"))
                .firstname("Thomas")
                .lastname("Wayne")
                .email("wayne@gotham.com")
                .age("40")
                .address("Gotham")
                .enabled(true)
                .build();
        user16 = User.builder()
                .username("MethMan")
                .password(encoder.encode("MethMan"))
                .firstname("Walter")
                .lastname("White")
                .email("breaking@bad.com")
                .age("46")
                .address("USA")
                .enabled(true)
                .build();
        user17 = User.builder()
                .username("MethManWife")
                .password(encoder.encode("MethManWife"))
                .firstname("Skyler")
                .lastname("White")
                .email("breaking@bad.com")
                .age("42")
                .address("USA")
                .enabled(true)
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
        user4.getAuthorities().add(authUser);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(user11);
        userRepository.save(user12);
    }
}
