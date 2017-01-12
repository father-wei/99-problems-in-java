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
        assertEquals(true,   questions31To40.isPrime(181));
        assertEquals(false,  questions31To40.isPrime(121));
        assertEquals(true,   questions31To40.isPrime(149));
        assertEquals(false,  questions31To40.isPrime(105));
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

    @Test
    public void testGeneratePrimesNumbers(){
        assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, Nil]",
                questions31To40.primes(100).toString());
    }

    @Test
    public void testQuestion35(){
        assertEquals("[3, 3, 5, 7, Nil]", questions31To40.primeFactors(315));
    }


}
