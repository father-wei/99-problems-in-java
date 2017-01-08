package problems.prolog.lists;
import static core.list.List.*;

import core.list.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestQuestions21To28 {
    Questions21To28 questions21To28 = new Questions21To28();

    //Insert an element at a given position into a list.
    @Test
    public void testQuestion21() {
        assertEquals("[a, new, b, c, d, Nil]", questions21To28.insertAt("new", 1, list('a', 'b', 'c', 'd')).toString());
    }

    //Create a list containing all integers within a given range.
    @Test
    public void testQuestion22(){
        assertEquals("[4, 5, 6, 7, 8, 9, Nil]", questions21To28.range.apply(4, 9).toString());
    }

    //Extract a given number of randomly selected elements from a list.
    @Test
    public void testQuestion23(){
        assertEquals(3, questions21To28.randomSelect(3, list('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h')).getLength());
    }


    //Lotto: Draw N different random numbers from the set 1..M.
    @Test
    public void testQuestion24(){
        List<Integer> ls = questions21To28.lotto(3, 10);
        assertEquals(3, ls.getLength());
        assertEquals(true, ls.head() <= 10);
        assertEquals(true, ls.tail().head() <= 10);
        assertEquals(true, ls.tail().tail().head() <= 10);
    }


    //Generate a random permutation of the elements of a list.
    @Test
    public void testQuestion25(){
        assertEquals(3, questions21To28.randomPermute(list(1,2,3)).getLength());
    }
}
