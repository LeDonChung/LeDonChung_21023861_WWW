package vn.edu.fit.student.donchung.backend.enums;

public enum SkillLevel {
    BEGINNER("BEGINNER", 1),
    INTERMEDIATE("INTERMEDIATE", 2),
    ADVANCED("ADVANCED", 3),
    PROFESSIONAL("PROFESSIONAL", 4),
    MASTER("MASTER", 5);

    private final String name;
    private final int levelValue;

    SkillLevel(String name, int levelValue) {
        this.name = name;
        this.levelValue = levelValue;
    }

    public String getName() {
        return name;
    }

    public int getLevelValue() {
        return levelValue;
    }

    @Override
    public String toString() {
        return name;
    }

    public static SkillLevel fromLevelValue(int levelValue) {
        for (SkillLevel level : SkillLevel.values()) {
            if (level.getLevelValue() == levelValue) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid level value: " + levelValue);
    }
}
