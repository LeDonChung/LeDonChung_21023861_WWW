package vn.edu.fit.student.donchung.backend.services;

import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.entities.Company;

import java.util.List;

public interface CompanyService {
    public List<Company> getAllList();


    List<MailDto> getAllEmails(Long companyId);
}
