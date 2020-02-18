package tests.demos;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MatchersDemo {

    @Test
    public void canCompareSomeStrings() {
        String s1 = "Lorem ipsum";
        String s2 = "ips";

        System.out.println(s1.contains(s2));
        assertThat(s1, containsString(s2));

        List<String> list = Arrays.asList("Foo", "Bar");

        assertThat(list, hasItems("Foo"));        // true if list contains the items, possibly among others
        assertThat(list, contains("Foo", "Bar")); // true if list items match EXACTLY - values, number and order of items
    }
}
