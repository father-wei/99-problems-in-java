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
    Function<List<T>, List<T>> duplicate =
            ls -> ls.flatMap(
                x -> list(x, x)
            );


    /*
     *    Question 15
     *    Duplicate the elements of a list a given number of times.
     */
    BiFunction<Integer,List<T>, List<T>> duplicateN =
            (n, ls) -> ls.flatMap(
                  x-> this.multiple.apply(n, x)
            );



}
