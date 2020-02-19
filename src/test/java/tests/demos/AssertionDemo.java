package tests.demos;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssertionDemo {

    @Test
    void canDoSomeStringAsserts() {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";

        assertTrue(s.contains("elit"));
        assertFalse(s.contains("elite"));
        assertThat(s, containsString("elit"));

        assertThat(s, equalTo("Lorem ipsum dolor sit amet, consectetur adipiscing elit"));
        assertThat(s, is("Lorem ipsum dolor sit amet, consectetur adipiscing elit"));

        assertThat(s, not(emptyOrNullString()));
        assertThat(s, startsWith("Lorem"));
        assertThat(s, either(endsWith("elit")).or(endsWith("other string")));
    }

//    TODO
    @Test
    void canDoSomeListAsserts() {

    }
}
