package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.entities.Company;
import vn.edu.fit.student.donchung.backend.repositories.CompanyRepository;
import vn.edu.fit.student.donchung.backend.services.CompanyService;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<Company> getAllList() {
        return companyRepository.findAll();
    }
}
