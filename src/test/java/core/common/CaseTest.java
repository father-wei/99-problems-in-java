package core.common;
import static org.junit.Assert.assertEquals;
import static core.common.Case.*;
import core.list.List;
import static core.list.List.*;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

public class CaseTest {

    /*
    *  test if input string is "hello", if it is, return "hello world", if "world",return "hello world", otherwise, return "not found"
    * */
    @Test
    public void caseTest(){


        Function<String, Result<String>> stringMatchTest =
                str -> match(
                    mCase(Result.empty()),
                    mCase(()-> str == "hello", ()->Result.success("HelloWorld")),
                    mCase(()-> str == "world", ()->Result.success("HelloWorld"))
                );


        assertEquals("Empty()",   stringMatchTest.apply(null).toString());
        assertEquals("HelloWorld", stringMatchTest.apply("hello").getOrElse("something else"));
        assertEquals("HelloWorld", stringMatchTest.apply("world").getOrElse("something else"));

       // assertEquals("hello world", stringMatchTest.apply("world"));
    }

    Function<List<Integer>, Result<Integer>> last = ls -> match(
            mCase(Result.failure("Not Found")),
            mCase(()-> ls.isEmpty(), ()-> Result.failure("Empty List")),
            mCase(()-> ls.tail().isEmpty(), ()-> Result.success(ls.head())),
            mCase(()-> !ls.tail().isEmpty(),()-> this.last.apply(ls.tail()) )
    );

    @Test
    public void testList(){
        //return the last element of the list
        List<Integer> list = list(1,2,3);
        CaseTest test = new CaseTest();

        Result result = test.last.apply(list);

        assertEquals(3, result.getOrElse(-9999));



        List<Integer> list2 = NIL;
        Result result2 = test.last.apply(list2);
        assertEquals(-9999, result2.getOrElse(-9999));




    }

}
