package vn.edu.iuh.fit.donchung.skill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.edu.iuh.fit.donchung.entity.Skill;
import vn.edu.iuh.fit.donchung.repositories.SkillRepository;

import java.util.List;

@SpringBootTest
public class SkillTest {
    @Autowired
    private SkillRepository skillRepository;


    @Test
    void testInsert() {
        Skill skill = Skill.builder()
                .skillName("MariaDB")
                .field("IT")
                .description("Java Developer")
                .build();

        skill = skillRepository.save(skill);
        Skill actual = skillRepository.findById(skill.getId());
        assert skill.getSkillName().equals(actual.getSkillName());
    }

    @Test
    void testUpdate() {
        Skill skill = Skill.builder()
                .id(1)
                .skillName("Java")
                .field("IT")
                .description("Java Developer")
                .build();

        skill = skillRepository.save(skill);
        Skill actual = skillRepository.findById(1);
        assert skill.getSkillName().equals(actual.getSkillName());
    }

    @Test
    void testFindById() {
        Skill skill = skillRepository.findById(1);
        assert skill.getId() == 1;
    }

    @Test
    void testDelete() {
        boolean result = skillRepository.delete(1);

        Skill skill = skillRepository.findById(1);
        assert skill == null;
    }

    @Test
    void testFindAll() {
        List<Skill> skills = skillRepository.findAll();

        skills.forEach(System.out::println);
    }
}
