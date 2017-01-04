package core.List;

import org.junit.Test;
import static core.List.List.*;
import static org.junit.Assert.assertEquals;


public class ListTest {
    @Test
    public void testToString() {
        assertEquals("[1, 2, 3, Nil]", list(1, 2, 3).toString());
    }

    @Test
    public void testHead() {
        assertEquals(Integer.valueOf(1) , list(1, 2, 3).head());
    }

    @Test
    public void testTail(){
        assertEquals( "[2, 3, Nil]" , list(1, 2, 3).tail());
    }


}
