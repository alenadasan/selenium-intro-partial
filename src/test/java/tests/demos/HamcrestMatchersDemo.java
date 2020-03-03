package tests.demos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatchersDemo {

    @Test
    void canAssertStuffDifferentWays() {
        String s = "Lorem ipsum";

        assertTrue(s.equals("Lorem ipsum"));
        assertFalse(s.equals("Lorem ipsumus"));
        assertEquals(s, "Lorem ipsum");

        assertThat(s, equalTo("Lorem ipsum"));
    }

    @Test
    void canDoSomeSimpleAsserts() {
        String s = "Lorem ipsum dolor sit amet";
        double grade = 99.8;

        assertThat(s, is("Lorem ipsum dolor sit amet"));
        assertThat(s, equalTo("Lorem ipsum dolor sit amet"));
        assertThat(s, equalToIgnoringCase("LOREM IPSUM DOLOR SIT amet"));

        assertThat(s, containsString("amet"));
        assertThat(s, containsStringIgnoringCase("AMEt"));

        assertThat(s, not(emptyOrNullString()));
        assertThat(s, not(nullValue()));

        assertThat(s, startsWith("Lorem"));
        assertThat(s, either(startsWith("Lorem")).or(endsWith("Lorem")));

        assertThat(s, allOf(
                startsWith("Lorem"),
                containsString("dolor"),
                endsWith("amet")
        ));

        assertThat(s, anyOf(
                containsString("dummy"),
                containsString("dolor")
        ));


        assertThat(grade, closeTo(100, 0.5));
        assertThat(grade, both(greaterThan(85.0)).and(lessThan(100.0)));
    }

    @Test
    void canDoSomeListAsserts() {
        List<String> list = Arrays.asList("Foo", "Bar", "Mar");

        assertThat(list, hasItem("Foo"));                // true if list contains the items, possibly among others
        assertThat(list, hasItems("Foo", "Bar"));        // true if list contains the items, possibly among others
        assertThat(list, contains("Foo", "Bar", "Mar")); // true if list items match EXACTLY - values, number and order of items
        assertThat(list, containsInAnyOrder("Bar", "Mar", "Foo"));
                                                         // true if list items match values and number of items, order of items may vary

        assertThat(list, everyItem(hasLength(3)));
        assertThat(list, hasSize(3));
    }

    @Test
    void canDoSomeMoreListAsserts() {
        List<String> s3 = Arrays.asList("dolor", "sit", "amet", "sum");

        assertThat(s3.size(), greaterThanOrEqualTo(2));
        assertThat(s3, hasItem("sit"));
        assertThat(s3, contains("dolor", "sit", "amet", "sum"));

        assertThat(s3, hasItems("amet", "sum"));

        assertThat(s3, anyOf(hasItem("sun"), hasItem("sum"), hasItem("sur")));
        assertThat(s3, either(hasItem("sun")).or(hasItem("sum")).or(hasItem("sur")));
    }
}
