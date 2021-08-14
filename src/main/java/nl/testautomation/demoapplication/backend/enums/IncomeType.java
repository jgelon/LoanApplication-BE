package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Locale;

public enum IncomeType {
    TEMPORARY_CONTRACT,
    PERMANENT_CONTRACT,
    SELF_EMPLOYED,
    NO_INCOME;

    @JsonCreator
    public static IncomeType fromString(String value) {
        return IncomeType.valueOf(value.replace(" ", "_").toUpperCase(Locale.ROOT));
    }
}
