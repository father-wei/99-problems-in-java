package problems.prolog.lists;

import core.common.Tuple;
import core.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;


import static core.common.Case.*;
import static core.list.List.*;

public class Questions11To20<T> {

    /*
     *    Question 11
     *    Modified run-length encoding.
     */
    public Function<List<T>, List> encodeModified =
            ls ->
                 new Questions1To10<T>().pack.andThen(this.encodeModified_).apply(ls);


    private Function<List<List<T>>, List> encodeModified_ =
            ls -> ls.map(
                x -> x.getLength() == 1?
                        x.head() :
                        new Tuple<Integer, T>(x.getLength(), x.head())
            );



    /*
     *    Question 12
     *    Decode a run-length encoded list
     */
    public Function<List<Tuple<Integer, T>>, List<T>> decode =
            x ->  x.flatMap(e -> this.decodeTuple.apply(e));


    private Function<Tuple<Integer, T>, List<T>> decodeTuple =
            x ->  this.multiple.apply(x._1, x._2);

    // not tail safe. but good enough for this small question
    private BiFunction<Integer, T, List<T>> multiple =
            (i, x) -> i == 0 ?
                    NIL :
                    list(x).concat(this.multiple.apply(i-1, x));



    /*
     *    Question 13
     *    Run-length encoding of a list (direct solution).
     */
    public Function<List<T>, List<Tuple<Integer, T>>> encodeDirect =
            ls -> ls.foldRight(
                NIL,
                (T x, List<Tuple<Integer, T>> acc) -> acc.isEmpty()?
                                        list(new Tuple<>(1, x)):
                                            acc.head()._2.equals(x) ?
                                                list(new Tuple<>(acc.head()._1  + 1, x)).concat(acc.tail()):
                                                list(new Tuple<>(1, x)).concat(acc)
            );


    /*
     *    Question 14
     *    Duplicate the elements of a list
     */
    public Function<List<T>, List<T>> duplicate =
            ls -> ls.flatMap(
                x -> list(x, x)
            );


    /*
     *    Question 15
     *    Duplicate the elements of a list a given number of times.
     */
    public BiFunction<Integer,List<T>, List<T>> duplicateN =
            (n, ls) -> ls.flatMap(
                  x-> this.multiple.apply(n, x)
            );



    /*
     *    Question 16
     *    Drop every Nth element from a list.
     */
    public BiFunction<Integer, List<T>, List<T>> drop =
            (n, ls) ->  this.drop_.apply(n).apply(n, ls);


    private Function<Integer, BiFunction<Integer, List<T>, List<T>>> drop_ =
            n -> (count, ls) ->
                    ls.isEmpty()?
                            NIL:
                                count == 1 ?
                                    this.drop_.apply(n).apply(n, ls.tail()):
                                    list(ls.head()).concat(this.drop_.apply(n).apply(count -1, ls.tail()));

    /*
     *    Question 17
     *    Split a list into two parts.
     */
    public BiFunction<Integer, List<T>, Tuple<List<T>, List<T>>> split =
            (n, ls) -> ls.isEmpty() ?
                            new Tuple<>(list(), list()) :
                                n == 0?
                                    new Tuple<>(list(), ls.tail()) :
                                    new Tuple<>(list(ls.head())
                                            .concat(this.split.apply(n-1, ls.tail())._1),
                                                    this.split.apply(n-1, ls.tail())._2);

    /*
     *    Question 18
     *    Extract a slice from a list.
     */
    private Function<Integer, Function<Integer, Function<List<T>, List<T>>>> slice =
            start -> end ->  ls ->
                    this.split.apply( end - start,
                            this.split.apply(start -1, ls)._2)._1;
    // curring cannot be call outside of class.. is this a java 8 bug?
    // wrap it into method
    public List<T> slice(int start, int end, List<T> ls){
        return this.slice.apply(start).apply(end).apply(ls);
    }


}
