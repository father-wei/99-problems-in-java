package problems.arithmetic;

import org.junit.Test;

import static core.list.List.list;
import static org.junit.Assert.assertEquals;

/**
 * Created by bwei3 on 1/10/17.
 */
public class TestQuestions31To41 {

    private Questions31To41 questions31To41 = new Questions31To41();

    //Determine whether a given integer number is prime.
    @Test
    public void testQuestion31(){
        assertEquals(true,   questions31To41.isPrime(181));
        assertEquals(false,  questions31To41.isPrime(121));
        assertEquals(true,   questions31To41.isPrime(149));
        assertEquals(false,  questions31To41.isPrime(105));
    }

    //Determine the greatest common divisor of two positive integer numbers.
    @Test
    public void testQuestion32(){
        assertEquals(9, questions31To41.gcd(63, 36));
    }

    //Determine whether two positive integer numbers are coprime.
    @Test
    public void testQuestion33(){
        assertEquals(true, questions31To41.isCoprimeTo(35, 64));
    }

    //Calculate Euler's totient function phi(m).
    @Test
    public void testQuestion34(){
        assertEquals(4, questions31To41.totient(10));
    }

    @Test
    public void testGeneratePrimesNumbers(){
        assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, Nil]",
                questions31To41.primes(2, 100).toString());
    }

    //Determine the prime factors of a given positive integer.
    @Test
    public void testQuestion35(){
        assertEquals("[3, 3, 5, 7, Nil]", questions31To41.primeFactors(315).toString());
    }


    //Determine the prime factors of a given positive integer (2).
    @Test
    public void testQuestion36(){
        assertEquals("[(3, 2), (5, 1), (7, 1), Nil]", questions31To41.primeFactorMultiplicity(315).toString());
    }


    //A list of prime numbers.
    @Test
    public void testQuestion39(){
        assertEquals("[7, 11, 13, 17, 19, 23, 29, 31, Nil]", questions31To41.listPrimesinRange(7, 31).toString());
    }

    //Goldbach's conjecture.
    @Test
    public void testQuestion40(){
        assertEquals("(5, 23)", questions31To41.goldbach(28).getOrThrow().toString());
    }


    //A list of Goldbach compositions.
    @Test
    public void testQuestion41(){
        assertEquals("[(10, (3, 7)), (12, (5, 7)), (14, (3, 11)), (16, (3, 13)), (18, (5, 13)), (20, (3, 17)), Nil]",
                questions31To41.printGoldbachList(9, 20).toString());
    }


    /*
        A list of Goldbach compositions -- verison b

        In most cases, if an even number is written as the sum of two prime numbers,
        one of them is very small. Very rarely, the primes are both bigger than, say, 50.
        Try to find out how many such cases there are in the range 2..3000.
     */
    @Test
    public void testQuestion41B(){
        assertEquals("[(992, (73, 919)), (1382, (61, 1321)), (1856, (67, 1789)), (1928, (61, 1867)), Nil]", questions31To41.printGoldbachListB(1, 2000, 50).toString());
    }


}
