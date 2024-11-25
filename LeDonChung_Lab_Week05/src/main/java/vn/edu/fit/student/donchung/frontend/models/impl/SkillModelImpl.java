package vn.edu.fit.student.donchung.frontend.models.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.edu.fit.student.donchung.frontend.dto.SkillDto;
import vn.edu.fit.student.donchung.frontend.models.SkillModel;
import vn.edu.fit.student.donchung.frontend.utils.AppUtils;

import java.util.List;

@Component
public class SkillModelImpl implements SkillModel {
    @Autowired
    private RestTemplate  restTemplate;
    @Override
    public List<SkillDto> getAll() {
        ResponseEntity<List<SkillDto>> response = restTemplate.exchange(
                AppUtils.API_URL + "/skills",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SkillDto>>() {}
        );
        return response.getBody();
    }
}
