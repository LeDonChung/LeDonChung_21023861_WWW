package vn.edu.iuh.fit.donchung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.edu.iuh.fit.donchung.entity.Candidate;
import vn.edu.iuh.fit.donchung.repositories.CandidateRepository;

import java.sql.Date;

@SpringBootApplication
public class LeDonChungLabWeek05Application {

    public static void main(String[] args) {
        SpringApplication.run(LeDonChungLabWeek05Application.class, args);
    }

}
