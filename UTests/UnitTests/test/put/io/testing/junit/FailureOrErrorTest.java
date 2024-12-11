package put.io.testing.junit;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;


public class FailureOrErrorTest {
    @Test
    public void test1(){
        assertEquals(1, 0);
    }
    @Test
    void test2() {
        // Error: nieoczekiwany wyjątek
        assertEquals(1/0, 0, "Error: Dzielenie przez zero");
    }
    @Test
    public void test3() {
        try {
            assertEquals(2, 3);
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("Klasa: " + t.getClass().getName());
        }
    }

}