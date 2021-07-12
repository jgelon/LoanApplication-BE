package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Locale;

public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    @JsonCreator
    public static Gender fromString(String value) {
        return Gender.valueOf(value.toUpperCase(Locale.ROOT));
    }
}
