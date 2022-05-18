package nl.testautomation.demoapplication.backend.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class MaritalStatusTest {

    @Test
    void fromString() {
        assertThat(MaritalStatus.fromString("Living Together"), is(MaritalStatus.LIVING_TOGETHER));
    }
}