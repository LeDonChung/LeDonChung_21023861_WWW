package vn.edu.fit.student.donchung.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.backend.entities.Company;

import java.util.List;

@SpringBootTest
public class CompanyTest {
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        ResponseEntity<List<Company>> response = restTemplate.exchange(
                "http://localhost:8080/api/companies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        List<Company> companies = response.getBody();

        assert companies != null;
        companies.forEach(System.out::println);

    }
}
