package tests.demos;

import org.junit.jupiter.api.*;

public class JUnitAnnotationsDemo {

    @BeforeAll
    public static void oneTimeSetup() {
        System.out.println("Running @BeforeAll");
    }

    @BeforeEach
    public void setup() {
        System.out.println("Running @BeforeEach");
    }

    @Test
    public void testOne() {
        System.out.println("Running @Test One");
    }

    @Test
    public void testTwo() {
        System.out.println("Running @Test Two");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Running @AfterEach");
    }

    @AfterAll
    public static void oneTimeTearDown() {
        System.out.println("Running @AfterAll");
    }
}
