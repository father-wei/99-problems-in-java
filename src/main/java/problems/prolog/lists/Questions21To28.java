package problems.prolog.lists;


import core.common.Result;
import core.common.Tuple;
import core.list.List;
import static core.list.List.*;

import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import static core.common.Case.*;


public class Questions21To28<T> {

    //Using util method from question 11 to 20
    Questions11To20<T> util = new Questions11To20<T>();

    /*
     *    Question 21
     *    Insert an element at a given position into a list.
     */
    public  List<T> insertAt(T data, int i, List<T> ls){
        return this.insertAt_.apply(data).apply(i).apply(ls);
    }

    private Function<T, Function<Integer, Function<List<T>, List<T>>>> insertAt_ =
            data -> index -> ls -> {
                Tuple<List<T>, List<T>> tuple = util.split.apply(index-1, ls);
                return tuple._1.concat(list(data)).concat(tuple._2);
            };


    /*
     *    Question 22
     *    Create a list containing all integers within a given range.
     */
    public BiFunction<Integer, Integer, List<Integer>> range =
            (start, end) ->
                    start > end?
                            NIL:
                            list(start).concat(this.range.apply(start + 1, end));


    /*
     *    Question 23
     *    Extract a given number of randomly selected elements from a list.
     */
    public List<T> randomSelect(int i, List<T> ls){
        return this.randomSelect_.apply(i, ls).getOrThrow();
    }

    private BiFunction<Integer, List<T>, Result<List<T>>> randomSelect_ =
            (count, ls) -> match(
                mCase(Result.failure("can not find the random select number")),
                mCase(() -> ls.getLength() <  count,  () -> Result.failure("elements select are more than list")),
                mCase(() -> count == 0,  () -> Result.success(NIL)),
                mCase(() -> count >  0,  () -> {
                    Tuple<List<T>, T> tuple = util.removeAt.apply(new Random().nextInt(ls.getLength()), ls);
                    return Result.success(list(tuple._2).concat(this.randomSelect_.apply(count-1, tuple._1).getOrThrow()));
                })
            );

    /*
     *    Question 24
     *    Lotto: Draw N different random numbers from the set 1..M.
     */

    public List<T> lotto(int count, int end){
        BiFunction<Integer, Integer, List<T>> lotto_ =
                (c, e) -> this.randomSelect(c, List.range(1, e));
        return lotto_.apply(count, end);
    }

    /*
     *    Question 25
     *    Generate a random permutation of the elements of a list.
     */
    public List<T> randomPermute( List<T> ls){
        return this.randomSelect(ls.getLength(), ls);
    }












}
