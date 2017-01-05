package problems.prolog.lists;

import org.junit.Test;

import static problems.prolog.lists.Questions1To28.*;
import static org.junit.Assert.assertEquals;
import static core.list.List.*;


public class TestQuestions1To28 {

    Questions1To28<Integer> questions = new Questions1To28<>();

    @Test
    public void testQuestion1(){
        int lastValue = questions.last.apply(list(1,2,3,4,5)).getOrElse(-9999999);
        assertEquals(5, lastValue);

        int errorTest = questions.last.apply(list()).getOrElse(-9999999);
        assertEquals(-9999999, errorTest);
    }


    @Test
    public void testQuestion2(){
        int lastButOneValue = questions.lastButOne.apply(list(1,2,3,4,5)).getOrElse(-9999999);
        assertEquals(4, lastButOneValue);

        int errorTest1 = questions.lastButOne.apply(list()).getOrElse(-9999999);
        assertEquals(-9999999, errorTest1);

        int errorTest2 = questions.lastButOne.apply(list(1)).getOrElse(-9999999);
        assertEquals(-9999999, errorTest2);

    }


    @Test
    public void testQuestion3(){
        int kthEle = questions.kthEle.apply(list( 0, 1, 2, 3, 4, 5) , 3).getOrElse(-999999);
        assertEquals(3, kthEle);

        int errorTest = questions.kthEle.apply(list(0, 1), 5).getOrElse(-9999999);
        assertEquals(-9999999, errorTest);

    }


}
