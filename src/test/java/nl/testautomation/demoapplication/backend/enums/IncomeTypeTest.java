package nl.testautomation.demoapplication.backend.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class IncomeTypeTest {

    @Test
    void fromString() {
        assertThat(IncomeType.fromString("No Income"), is(IncomeType.NO_INCOME));
    }
}