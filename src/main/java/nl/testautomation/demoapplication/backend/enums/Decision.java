package nl.testautomation.demoapplication.backend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Locale;

public enum Decision {
    OPEN,
    APPROVED,
    DECLINED;

    @JsonCreator
    public static Decision fromString(String value) {
        return Decision.valueOf(value.toUpperCase(Locale.ROOT));
    }
    
}
