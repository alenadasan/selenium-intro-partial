package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AnnotationsTestDemo {

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

        WebDriver driver = new ChromeDriver();
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
