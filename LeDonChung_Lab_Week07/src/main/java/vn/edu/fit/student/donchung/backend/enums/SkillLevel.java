package vn.edu.fit.student.donchung.backend.enums;

import lombok.ToString;

public enum SkillLevel {
    MASTER("MASTER"),
    BEGINER("BEGINER"),
    ADVANCED("ADVANCED"),
    PROFESSIONAL("PROFESSIONAL"),
    IMTERMEDIATE("IMTERMEDIATE");

    private String name;

    SkillLevel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
