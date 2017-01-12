package problems.arithmetic;

import core.common.Result;
import core.common.Tuple;
import core.list.List;
import problems.prolog.lists.Questions1To10;

import static core.list.List.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Questions31To41 {

    Questions1To10<Integer> util = new Questions1To10();

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
        return primeFactors_.apply(i, primes(2, i));
    }

    private BiFunction<Integer, List<Integer>, List<Integer>> primeFactors_ =
            (i, primes) ->
                    isPrime(i) ?
                            list(i):
                            i % primes.head() == 0?
                                 list(primes.head()).concat(this.primeFactors_.apply(i / primes.head(), primes)):
                                 this.primeFactors_.apply(i, primes.tail());




    // Having some fun with java 8 stream
    public List<Integer> primes(int start, int end){
        int[] p =
        IntStream.rangeClosed(start, end)
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


    /*
     *    Question 36
     *    Determine the prime factors of a given positive integer (2).
     */
    public List<Tuple<Integer,Integer>> primeFactorMultiplicity(int i){
        Function <Integer, List<Integer>>  primeFactorsFn = x-> primeFactors(x);
        return primeFactorsFn.andThen(util.encode)
                             .apply(i)
                             .map(tuple -> new Tuple<Integer, Integer>(tuple._2, tuple._1));


    }


    /*Don't know what this really do.... refactor scala varsion
     *    @link http://aperiodic.net/phil/scala/s-99/
     *
     *    Question 37
     *    DCalculate Euler's totient function phi(m) (improved).
     */
    public int totientImpr(int start){
        return primeFactorMultiplicity(start)
                .foldLeft(1, (r, f) ->
                        r * (f._1 - 1) * (int) Math.pow(f._1, f._2 - 1));
    }

    /*
     *    Question 38
     *    Compare the two methods of calculating Euler's totient function.
     */

    // this question is for performance test
    // so test in testing code, not here


    /*
     *    Question 39
     *    A list of prime numbers.
     */
    public List<Integer> listPrimesinRange(int start, int end) {
        return primes(start, end);
    }



    /*
     *    Question 40
     *    Goldbach's conjecture.
     */
    public  Result<Tuple<Integer, Integer>> goldbach(int num){
        return num % 2 == 0 ?
                Result.<Tuple<Integer, Integer>>success(goldbach_.apply(num, listPrimesinRange(2, num))) :
                num < 2?
                        Result.failure("Number has to be even number greater than 2"):
                        Result.failure("Number has to be even number greater than 2");
    }

    private BiFunction<Integer,List<Integer>, Tuple<Integer, Integer>> goldbach_ =
            (num, ls) ->  isPrime(num - ls.head())?
                    new Tuple<>(ls.head(), num - ls.head()) :
                    this.goldbach_.apply(num, ls.tail());


    /*
     *    Question 41
     *    A list of Goldbach compositions.
     */
    public List<Tuple<Integer, Tuple<Integer, Integer>>> printGoldbachList(int start, int end){
        int[] ins = IntStream.rangeClosed(start, end)
                    .filter(x -> x % 2 == 0)
                    .toArray();

        List<Integer> ls = arryToList(ins);

        return ls.map(x-> new Tuple<>(x, goldbach(x).getOrThrow()));
    }

    private List<Integer> arryToList(int[] ints){
        List ls = list();
        for (int i : ints){
            ls = list(i).concat(ls);
        }
        return ls.reverse();
    }

    public List<Tuple<Integer, Tuple<Integer, Integer>>> printGoldbachListB(int start, int end, int primeStart){
        return printGoldbachList(start, end)
                    .foldRight(NIL, (e, acc)-> e._2._1 > primeStart && e._2._2 > primeStart?
                            list(e).concat(acc):
                            acc);
    }

}
