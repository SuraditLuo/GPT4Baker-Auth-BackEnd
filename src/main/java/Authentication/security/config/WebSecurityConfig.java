package Authentication.security.config;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import Authentication.security.controller.JwtAuthenticationEntryPoint;
import Authentication.security.controller.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    @Autowired
    private final JwtAuthenticationTokenFilter tokenFilter;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .antMatchers("/auth/**", "/register").permitAll()
                .antMatchers(HttpMethod.GET,"/patient").hasAnyRole("DOCTOR", "ADMIN")
                .antMatchers(HttpMethod.GET,"/vaccines").permitAll()
                .antMatchers(HttpMethod.GET,"/patient/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/doctor/{id}").hasAnyRole("DOCTOR", "ADMIN")
                .antMatchers(HttpMethod.GET,"/doctor").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers(HttpMethod.GET,"/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/user/{id}").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST,"/add-patient-role").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/add-doctor-role").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/comment").hasRole("DOCTOR")
                .antMatchers(HttpMethod.POST,"/remove-doctor-role").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/save-doctor").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/save-vaccine").hasRole("ADMIN")
                .anyRequest()
                .authenticated();
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("security filter chain set");
        return http.build();
    }
    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)  {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }



    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}