package problems.prolog.lists;

import core.common.Result;
import core.common.Tuple;
import core.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    private BiFunction<Integer, T, List<T>> multiple =
            (i, x) -> i == 0 ?
                    NIL :
                    list(x).concat(this.multiple.apply(i-1, x));


    


}
