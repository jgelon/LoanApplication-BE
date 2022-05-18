package nl.testautomation.demoapplication.backend.enums;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GenderTest {

    @Test
    void fromString() {
        assertThat(Gender.fromString("Female"), is(Gender.FEMALE));
    }
}