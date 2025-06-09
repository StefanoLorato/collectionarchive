package org.generation.italy.collectionarchive.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class PasswordConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) //disattiva protezione CSRF
//Con un token CSRF ogni form (o richiesta sensibile) contiene un token univoco e temporaneo.
//Il server verifica che il token ricevuto nella richiesta corrisponda a quello salvato nella sessione dell’utente.Se non combacia? 403 Forbidden.
//(Cross-Site Request Forgery) è un attacco informatico che sfrutta la fiducia che un'applicazione web ha nei confronti dell'utente autenticato.
//Spring Security, per esempio, lo fa in automatico per le richieste POST, PUT, DELETE, ecc., e lo disattiva per le GET perché non dovrebbero mai causare effetti collaterali.
             .authorizeHttpRequests(auth -> auth
             .anyRequest().permitAll() //permette tutto
             );
        return http.build();
    }

    //@Bean
    //public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
    //}

}
