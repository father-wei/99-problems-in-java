package problems.arithmetic;

import org.junit.Test;

import static core.list.List.list;
import static org.junit.Assert.assertEquals;

/**
 * Created by bwei3 on 1/10/17.
 */
public class TestQuestions31To40 {

    private Questions31To40 questions31To40 = new Questions31To40();

    //Determine whether a given integer number is prime.
    @Test
    public void testQuestion31(){
        assertEquals(true,  questions31To40.isPrime(181));
        assertEquals(false, questions31To40.isPrime(121));
        assertEquals(true,  questions31To40.isPrime(149));
    }

    //Determine the greatest common divisor of two positive integer numbers.
    @Test
    public void testQuestion32(){
        assertEquals(9, questions31To40.gcd(63, 36));
    }

    //Determine whether two positive integer numbers are coprime.
    @Test
    public void testQuestion33(){
        assertEquals(true, questions31To40.isCoprimeTo(35, 64));
    }

    @Test
    public void testQuestion34(){
        assertEquals(4, questions31To40.totient(10));
    }




}
