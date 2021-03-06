package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum MaritalStatus {
    SINGLE,
    MARRIED,
    REGISTERED_PARTNERS,
    LIVING_TOGETHER;

    @JsonCreator
    public static MaritalStatus fromString(String value) {
        return MaritalStatus.valueOf(value.replace(" ", "_").toUpperCase(Locale.ROOT));
    }
}
