package problems.arithmetic;

import core.list.List;

import static core.list.List.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Questions31To40 {

    /*
     *    Question 31
     *    Determine whether a given integer number is prime.
     */
    public boolean isPrime(int i){
        return isPrime_.apply(2).apply((int)Math.sqrt(i)).apply(i);
    }

    private Function<Integer, Function<Integer, Function<Integer, Boolean>>> isPrime_ =
            start -> end -> num ->
                    start > end ?
                        true:
                            num % start == 0 ?
                                false:
                                this.isPrime_.apply(start + 1).apply(end).apply(num);



    /*
     *    Question 32
     *    Determine the greatest common divisor of two positive integer numbers.
     */
    public int gcd(int x, int y){
        return gcd_.apply(x, y);
    }

    private BiFunction<Integer, Integer, Integer> gcd_ =
            (x, y) ->
                  x == 0 ?
                         y :
                         this.gcd_.apply(y % x, x);

    /*
     *    Question 33
     *    Determine whether two positive integer numbers are coprime.
     */
    public boolean isCoprimeTo(int x, int y){
        return gcd(x, y) == 1;
    }

    /*
     *    Question 34
     *    Calculate Euler's totient function phi(m).
     */
    //Euler's so-called totient function phi(m) is defined as the number of positive integers r (1 <= r <= m) that are coprime to m.
    public int totient(int i){
        return List.range(1, i)
                   .foldRight( 0,
                              (x,  acc)-> isCoprimeTo(i, (int)x) ?
                                      acc + 1 :
                                      acc
                   );
    }

    /*
     *    Question 35
     *    Determine the prime factors of a given positive integer.
     */
    public List<Integer> primeFactors(int i){
        return primeFactors_.apply(i, primes(i));
    }

    private BiFunction<Integer, List<Integer>, List<Integer>> primeFactors_ =
            (i, primes) ->
                    isPrime(i) ?
                            list(i):
                            i % primes.head() == 0?
                                 list(primes.head()).concat(this.primeFactors_.apply(i / primes.head(), primes)):
                                 this.primeFactors_.apply(i, primes.tail());




    // Having some fun with java 8 stream
    public List<Integer> primes(int end){
        int[] p =
        IntStream.rangeClosed(2, end)
                .parallel()
                .filter( i ->
                        IntStream.rangeClosed (2, (int)Math.sqrt(i))
                                .parallel()
                                .allMatch     (x -> i % x != 0)
                ).toArray();

        List ls = list();

        for(int i : p){
            ls = list(i).concat(ls);
        }

        return ls.reverse();
    }




}
