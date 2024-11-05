package vn.edu.fit.student.donchung.backend.enums;

public enum SkillType {
    SOFT_SKILL("SOFT_SKILL"),
    UNSPECIFIC("UNSPECIFIC"),
    TECHNICAL_SKILL("TECHNICAL_SKILL");


    private String name;
    SkillType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
