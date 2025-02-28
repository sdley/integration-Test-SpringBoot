package sn.sdley.springtestintegration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SpringTestIntegrationApplicationTests {

    @Test
    void contextLoads() {
        // If the application context fails to load, this test will fail
    }

    @Test
    void mainMethodShouldRunWithoutExceptions() {
        // Calling the main() method and ensuring no exception occurs
        assertDoesNotThrow(() -> SpringTestIntegrationApplication.main(new String[]{}));
    }

}
