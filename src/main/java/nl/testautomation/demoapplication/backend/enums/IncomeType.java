package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum IncomeType {
    TEMPORARY_CONTRACT,
    PERMANENT_CONTRACT,
    SELF_EMPLOYED,
    NO_INCOME;

    private static final Map<String, IncomeType> mapping = new HashMap<>();

    static {
        mapping.put("Temporary contract", TEMPORARY_CONTRACT);
        mapping.put("Permanent contract", PERMANENT_CONTRACT);
        mapping.put("Self-employed", SELF_EMPLOYED);
        mapping.put("No income", NO_INCOME);
    }

    @JsonCreator
    public static IncomeType fromString(String value) {
        return mapping.get(value.toUpperCase(Locale.ROOT));
    }
}
