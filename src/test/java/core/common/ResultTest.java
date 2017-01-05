package core.common;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ResultTest {

    @Test
    public void testSuccess(){
        Result<String> success = Result.success("Hello World");
        assertEquals("Success(Hello World)", success.toString());
    }

    @Test
    public void testEmpty(){
        Result<String> empty = Result.empty();
        assertEquals("Empty()", empty.toString());
    }

    @Test
    public void testFailureWithString(){
        Result<String> failure = Result.failure("Message");
        assertEquals("Failure(Message)", failure.toString());
    }

    @Test
    public void testFailureWithException(){
        Result failure = Result.failure(new Exception("Exception"));
        assertEquals("Failure(Exception)", failure.toString());
    }

    @Test
    public void testFailureWithRuntimeException(){
        Result failure = Result.failure(new IllegalStateException("Illegale State Exception"));
        assertEquals("Failure(Illegale State Exception)", failure.toString());
    }

    @Test
    public void testSuccessGetOrElse(){
        Result<String> success = Result.success("Hello World");
        assertEquals("Hello World", success.getOrElse("something else"));

    }

    @Test
    public void testEmptyGetOrElse(){
        Result<String> empty = Result.empty();
        assertEquals("Empty", empty.getOrElse("Empty"));

    }

    @Test
    public void testFailureGetOrElse(){
        Result<String> failure = Result.failure("failure");
        assertEquals("Something else", failure.getOrElse("Something else"));

        Result<String> exception = Result.failure(new Exception("exception"));
        assertEquals("Something else", exception.getOrElse("Something else"));

        Result<String> runtimeException = Result.failure(new IllegalStateException("runtimeException"));
        assertEquals("Something else", runtimeException.getOrElse("Something else"));
    }


    @Test
    public void testSuccessOrElse(){
        Result<String> success = Result.success("Hello World");
        assertEquals("Success(Hello World)", success.orElse(()-> Result.success("something else")).toString());
    }

    @Test
    public void testEmplyOrElse(){
        Result<String> empty = Result.empty();
        assertEquals("Success(something else)", empty.orElse(()-> Result.success("something else")).toString());
    }

    @Test
    public void testFailureOrElse(){
        Result<String> failure = Result.failure("string failure");
        assertEquals("Success(something else)", failure.orElse(()-> Result.success("something else")).toString());

        Result<String> exception = Result.failure(new Exception("Exception"));
        assertEquals("Success(something else)", exception.orElse(()-> Result.success("something else")).toString());


        Result<String> runtimeException = Result.failure(new IllegalStateException("Exception"));
        assertEquals("Success(something else)", runtimeException.orElse(()-> Result.success("something else")).toString());
    }

    @Test
    public void testMap(){
        Result<String> success = Result.success("Success");
        assertEquals("Success(SuccessMap)", success.map(x -> x + "Map").toString());

        Result<String> empty = Result.empty();
        assertEquals("Empty()", empty.map(x -> x + "something").toString());

        Result<String> failure = Result.failure("error");
        assertEquals("Failure(error)", failure.map(x -> x + "something").toString());
    }


    @Test
    public void testFlatMap(){

        Result<String> success = Result.success("Success");

        assertEquals("Success(SuccessMap)", success.flatMap(x -> Result.success(x + "Map")).toString());

        assertEquals("Empty()", success.flatMap(x -> Result.empty()).toString());

        assertEquals("Failure(error)", success.flatMap(x -> Result.failure("error")).toString());

    }


}
