package vn.edu.iuh.fit.donchung.test;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.donchung.backend.entities.User;
import vn.edu.iuh.fit.donchung.backend.repositories.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Locale;

@SpringBootTest
public class RandomTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testInsertUser() {
        Faker faker = new Faker(new Locale("en"));

        for (int i = 1; i <= 200; i++) {
            User user = User.builder()
                    .email(faker.internet().emailAddress())
                    .intro(faker.disease().internalDisease())
                    .mobile(faker.phoneNumber().phoneNumber())
                    .passwordHash("default")
                    .lastName(faker.name().lastName())
                    .firstName(faker.name().firstName())
                    .profile(faker.internet().username())
                    .middleName(faker.name().nameWithMiddle())
                    .registeredAt(Timestamp.from(Instant.now()))
                    .lastLogin(Timestamp.from(Instant.now()))
                    .build();

            userRepository.save(user);
        }
    }
}
