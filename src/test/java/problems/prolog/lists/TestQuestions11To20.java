package problems.prolog.lists;
import core.list.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static core.list.List.*;
import core.common.Tuple;

import java.util.function.Function;
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

    //Run-length encoding of a list (direct solution).
    @Test
    public void testQuestion13(){
        assertEquals("[(2, a), (3, b), (1, c), Nil]", testInstance.encodeDirect.apply(list('a', 'a', 'b', 'b', 'b', 'c')).toString());

    }

    //Duplicate the elements of a list
    @Test
    public void testQuestion14(){
        assertEquals("[a, a, b, b, c, c, c, c, d, d, Nil]", testInstance.duplicate.apply(list('a', 'b', 'c', 'c', 'd')).toString());
    }


    //Duplicate the elements of a list a given number of times.
    @Test
    public void testQuestion15(){
        assertEquals("[a, a, a, b, b, b, c, c, c, c, c, c, d, d, d, Nil]",  testInstance.duplicateN.apply(3, list('a', 'b', 'c', 'c', 'd')).toString());
    }


    //Drop every Nth element from a list.
    @Test
    public void testQuestion16(){
        assertEquals("[a, b, d, e, g, h, j, k, Nil]", testInstance.drop.apply (3, list('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')).toString());
    }


    //Split a list into two parts.
    @Test
    public void testQuestion17(){
        assertEquals("([a, b, c, Nil], [e, f, g, h, i, j, k, Nil])", testInstance.split.apply(3, list('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')).toString());
    }

    //Extract a slice from a list.
    @Test
    public void testQuestion18(){
        assertEquals("[d, e, f, g, Nil]",  testInstance.slice(3, 7, list('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')).toString());

    }
}
