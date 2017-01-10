package problems.arithmetic;

import core.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by bwei3 on 1/10/17.
 */
public class Questions31To40 {

    /*
     *    Question 31
     *    Determine whether a given integer number is prime.
     */
    public boolean isPrime(int i){
        return isPrime_.apply(2).apply(Math.sqrt(i));
    }

    private Function<Integer, Function<Double, Boolean>> isPrime_ =
            start -> num ->
                    start > num ?
                        true:
                        num % start == 0 ?
                                false:
                                this.isPrime_.apply(start + 1).apply(num);



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

        return null;
    }




}
