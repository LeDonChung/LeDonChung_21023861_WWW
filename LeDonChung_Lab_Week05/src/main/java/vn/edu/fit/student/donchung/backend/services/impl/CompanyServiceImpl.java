package vn.edu.fit.student.donchung.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.fit.student.donchung.backend.converters.MailMapper;
import vn.edu.fit.student.donchung.backend.dtos.MailDto;
import vn.edu.fit.student.donchung.backend.entities.Company;
import vn.edu.fit.student.donchung.backend.entities.Job;
import vn.edu.fit.student.donchung.backend.entities.Mail;
import vn.edu.fit.student.donchung.backend.repositories.CompanyRepository;
import vn.edu.fit.student.donchung.backend.services.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MailMapper mailMapper;
    @Override
    public List<Company> getAllList() {
        return companyRepository.findAll();
    }

    @Override
    public List<MailDto> getAllEmails(Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isEmpty()) {
            return null;
        }

        List<Job> jobs = company.get().getJobs();

        // Convert jobs to mailDto
        List<Mail> mails = new ArrayList<>();
        for(Job job : jobs) {
            mails.addAll(job.getMails());
        }

        return mails.stream().map(mailMapper::toDto).toList();
    }
}
