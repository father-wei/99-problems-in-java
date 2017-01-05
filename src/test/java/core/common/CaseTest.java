package core.common;
import static org.junit.Assert.assertEquals;
import static core.common.Case.*;

import com.sun.org.apache.regexp.internal.RE;
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

}
