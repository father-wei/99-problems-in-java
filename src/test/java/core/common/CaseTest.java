package core.common;
import static org.junit.Assert.assertEquals;
import static core.common.Case.*;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.Supplier;

public class CaseTest {

    /*
    *  test if input string is "hello", if it is, return "hello world", if "world",return "hello world", otherwise, return "not found"
    * */
    @Test
    public void caseTest(){

        Supplier<String> defalutResult = ()-> "not found";
        Supplier<String> result = ()-> "hello world";

        Function<String, String> stringMatchTest =
                str -> match(
                    mCase(defalutResult),
                    mCase(()-> str == "hello", result),
                    mCase(()-> str == "world", result)
                );

        assertEquals("not found",   stringMatchTest.apply(null));
        assertEquals("not found",   stringMatchTest.apply("something else"));
        assertEquals("hello world", stringMatchTest.apply("hello"));
        assertEquals("hello world", stringMatchTest.apply("world"));
    }

}
