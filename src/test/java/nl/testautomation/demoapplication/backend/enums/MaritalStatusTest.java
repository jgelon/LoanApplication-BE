package nl.testautomation.demoapplication.backend.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MaritalStatusTest {

    @Test
    void fromString() {
        assertThat(MaritalStatus.fromString("Living Together"), is(MaritalStatus.LIVING_TOGETHER));
    }
}