package org.generation.italy.collectionarchive.masteruser;

import org.generation.italy.collectionarchive.models.entities.User;
import org.generation.italy.collectionarchive.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MasterUser implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("moderator").isEmpty()) {
            User user = new User();
            user.setUsername("moderator");
            user.setPassword(passwordEncoder.encode("moderator123"));
            user.setRole("MODERATOR");
            user.setActive(true);
            userRepository.save(user);
            System.out.println("Utente creato!");
        }
    }
}




