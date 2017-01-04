package core.tailcall;

import java.util.function.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static core.tailcall.TailCall.*;


public class TailCallTest {

    public static Function<Integer , Integer>
            factorial =
                x-> TailCallTest.factorialTail.apply(x).apply(1).eval();


    public static  Function<Integer, Function<Integer, TailCall<Integer>>>
            factorialTail =
                x -> acc -> x == 0 ?
                    ret(acc) :
                    sus(() ->  TailCallTest.factorialTail.apply(x-1).apply(acc * x));



    @Test
    public void testTests() {

        assertEquals(Integer.valueOf(120), TailCallTest.factorial.apply(5));
    }

}
