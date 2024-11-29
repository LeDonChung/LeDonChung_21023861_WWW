package vn.edu.fit.student.donchung.backend.services;

import java.util.Map;

public interface ThymeleafService {
    public String createContent(String template, Map<String, Object> variables);
}
