package vn.edu.iuh.fit.donchung.candidate;

import org.assertj.core.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.donchung.LeDonChungLabWeek05Application;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;
import vn.edu.iuh.fit.donchung.repositories.impl.CandidateRepositoryImpl;

import javax.sql.DataSource;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.logging.Logger;

@SpringBootTest(classes = {LeDonChungLabWeek05Application.class,
        CandidateTest.SpringDataJdbcConfig.class})
public class CandidateTest {
    @Autowired
    private CandidateRepository candidateRepository;

    private final Logger logger = Logger.getLogger(CandidateTest.class.getName());


    @Test
    void testInsert() {
        Candidate candidate =
                Candidate.builder()
                        .fullName("Le Don Chung")
                        .email("ledonchung@gmail.com")
                        .phone("0867713557")
                        .address("44/10 ")
                        .dob(Date.valueOf("1999-01-01"))
                        .build();

        Candidate candidateNew = candidateRepository.save(candidate);
        logger.info("Candidate: " + candidateNew);
        assert candidateNew.getId() != null;
    }


    @EnableJdbcAuditing
    @EnableJdbcRepositories(repositoryImplementationPostfix = "SpringJdbcImpl")
    public static class SpringDataJdbcConfig extends JdbcConfiguration {

        @Override
        protected JdbcCustomConversions jdbcCustomConversions() {
            return new JdbcCustomConversions(Collections.singletonList(new Converter<Clob, String>() {
                @Override
                public String convert(Clob clob) {
                    try {
                        return clob == null ? null : clob.getSubString(1L, (int) clob.length());
                    } catch (SQLException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }));
        }

        @Bean
        NamingStrategy namingStrategy() {
            return new NamingStrategy() {
                @Override
                public String getReverseColumnName(RelationalPersistentProperty property) {
                    return NamingStrategy.super.getReverseColumnName(property).toLowerCase() + "_id";
                }

                @Override
                public String getKeyColumn(RelationalPersistentProperty property) {
                    return "sort_order";
                }
            };
        }

        @Bean
        AuditorAware<String> auditorAware() {
            return new MyAuditorAware();
        }

    }
}
