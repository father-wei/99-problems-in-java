package hello;

import org.junit.Test;
import static org.junit.Assert.*;

public class GreeterTest {

    @Test
    public void testTests() {
        assertEquals(2, Greeter.add(1, 1));
    }

}