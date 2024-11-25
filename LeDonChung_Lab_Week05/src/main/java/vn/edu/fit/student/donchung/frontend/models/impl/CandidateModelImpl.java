package vn.edu.fit.student.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.frontend.dto.CandidateSkillDto;
import vn.edu.fit.student.donchung.frontend.models.CandidateModel;
import vn.edu.fit.student.donchung.frontend.utils.AppUtils;

import java.util.List;

@Component
public class CandidateModelImpl implements CandidateModel {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<CandidateSkillDto> getSkillsByCandidateId(Long candidateId) {
        ResponseEntity<List<CandidateSkillDto>> response = restTemplate.exchange(
                AppUtils.API_URL + "/candidates/" + candidateId + "/skills",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }
}
