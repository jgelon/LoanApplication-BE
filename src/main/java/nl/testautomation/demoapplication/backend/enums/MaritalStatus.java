package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Locale;

public enum MaritalStatus {
    SINGLE,
    MARRIED,
    REGISTERED_PARTNERS,
    LIVING_TOGETHER;

    @JsonCreator
    public static Gender fromString(String value) {
        return Gender.valueOf(value.replace(" ", "_").toUpperCase(Locale.ROOT));
    }
}
