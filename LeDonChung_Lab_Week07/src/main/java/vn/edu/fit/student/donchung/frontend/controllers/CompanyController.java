package vn.edu.fit.student.donchung.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.backend.entities.Company;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('COMPANY')")
    public String list() {
        ResponseEntity<List<Company>> response = restTemplate.exchange(
                "http://localhost:8082/api/companies",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Company>>() {});

        List<Company> companies = response.getBody();

        assert companies != null;
        companies.forEach(System.out::println);
        return "index";
    }

}
