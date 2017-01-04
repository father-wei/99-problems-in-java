package core.List;

import org.junit.Test;
import static core.List.List.*;
import static org.junit.Assert.assertEquals;


public class ListTest {
    @Test
    public void testTests() {
        assertEquals("[1, 2, 3, Nil]", list(1, 2, 3).toString());
    }



}
