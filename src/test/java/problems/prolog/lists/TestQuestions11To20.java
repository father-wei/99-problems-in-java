package problems.prolog.lists;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static core.list.List.*;
import core.common.Tuple;

import java.util.stream.IntStream;

public class TestQuestions11To20 {

    Questions11To20 testInstance = new Questions11To20();


    //Modified run-length encoding.
    @Test
    public void testQuestion11(){
        assertEquals("[(2, a), (3, b), c, Nil]", testInstance.encodeModified.apply(list('a', 'a', 'b', 'b', 'b', 'c')).toString());
    }


    //Decode a run-length encoded list
    @Test
    public void testQuestion12(){
        assertEquals("[a, a, a, b, b, Nil]", testInstance.decode.apply(list(new Tuple<>(3, 'a'), new Tuple<>(2, 'b'))).toString());
    }

}
