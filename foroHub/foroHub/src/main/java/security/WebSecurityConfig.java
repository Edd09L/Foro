package security;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.GET, "/topico/prueba").permitAll();

                    http.anyRequest().denyAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfiguration) throws Exception{
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passEnconder());
        provider.setUserDetailsService(userDetailsService());

        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("edwin")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());

        userDetailsList.add(User.withUsername("estuardo")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public PasswordEncoder passEnconder(){
        return NoOpPasswordEncoder.getInstance();
    }
}