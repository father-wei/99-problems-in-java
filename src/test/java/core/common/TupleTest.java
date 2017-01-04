package core.common;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by bwei3 on 1/4/17.
 */
public class TupleTest {
    @Test
    public void testTuple(){
        Tuple<String, Integer> tupleTest = new Tuple<>("hello", 123);
        assertEquals( tupleTest._1, "hello");
        assertEquals( tupleTest._2,  Integer.valueOf(123));

    }
}
