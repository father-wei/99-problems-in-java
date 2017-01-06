package problems.prolog.lists;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static core.list.List.*;


public class TestQuestions1To10 {

    Questions1To10<Integer> questions = new Questions1To10<>();

    //Find the last element of a list.
    @Test
    public void testQuestion1(){

        int lastValue = questions.last.apply(list(1,2,3,4,5)).getOrThrow();
        assertEquals(5, lastValue);

        int errorTest = questions.last.apply(list()).getOrElse(-9999999);
        assertEquals(-9999999, errorTest);
    }

    //Find the last but one element of a list.
    @Test
    public void testQuestion2(){

        int lastButOneValue = questions.lastButOne.apply(list(1,2,3,4,5)).getOrThrow();
        assertEquals(4, lastButOneValue);

        int errorTest1 = questions.lastButOne.apply(list()).getOrElse(-9999999);
        assertEquals(-9999999, errorTest1);

        int errorTest2 = questions.lastButOne.apply(list(1)).getOrElse(-9999999);
        assertEquals(-9999999, errorTest2);

    }


    //Find the Kth element of a list.
    @Test
    public void testQuestion3(){

        int kthEle = questions.kthEle.apply(list( 0, 1, 2, 3, 4, 5) , 3).getOrThrow();
        assertEquals(3, kthEle);

        int errorTest = questions.kthEle.apply(list(0, 1), 5).getOrElse(-9999999);
        assertEquals(-9999999, errorTest);

    }

    //Find the number of elements of a list.
    @Test
    public void testQuestion4(){

        assertEquals(Integer.valueOf(5), questions.length.apply(list(1,2,3,4,5)));
        assertEquals(Integer.valueOf(0), questions.length.apply(list()));

    }

    //Reverse a list
    @Test
    public void testQuestion5(){

        assertEquals("[5, 4, 3, 2, 1, Nil]", questions.reverse.apply(list(1,2,3,4,5)).toString());
    }

    //Find out whether a list is a palindrome
    @Test
    public void testQuestion6(){

        assertEquals(true, questions.isPalindrome.apply(list(1,2,2,1)) );
        assertEquals(false, questions.isPalindrome.apply(list(1,2,2,1,1)) );
    }


    // Flatten a nested list structure.
    @Test
    public void testQuestion7() {

        assertEquals("[1, 2, 3, Nil]", questions.flatten.apply(list(1, list(2,3))).toString());

        assertEquals("[1, 2, 3, Nil]", questions.flatten.apply(list(1, list(2, list(3)))).toString());

    }


    //Eliminate consecutive duplicates of list elements.
    @Test
    public void testQuestion8() {
        assertEquals("[1, 2, 3, Nil]", questions.compress.apply(list(1,1,2,2,3,3,3)).toString());
    }


    //Pack consecutive duplicates of list elements into sublists.
    @Test
    public void testQuestion9() {
        assertEquals("[[1, 1, Nil], [2, 2, Nil], Nil]", questions.pack.apply(list(1,1,2,2)).toString());
    }


    //Run-length encoding of a list.
    @Test
    public void testQuestion10(){
        assertEquals("[(2, 1), (2, 2), Nil]", questions.encode.apply(list(1,1,2,2)).toString());
    }
}
