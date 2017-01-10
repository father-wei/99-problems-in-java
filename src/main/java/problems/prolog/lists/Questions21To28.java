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


    /*
     *    Question 26
     *    Generate the combinations of K distinct objects chosen from the N elements of a list.
     */
    public List<List<T>> combinations(int i, List<T> ls){
        return ls.getLength() > i ?
                    this.combinations_.apply(i, ls).concat(this.combinations(i, ls.tail())):
                    list(ls);
    }

    private BiFunction<Integer, List<T>, List<List<T>>> combinations_ =
            (i, ls) ->
                    i == 1 ?
                       ls.map( x-> list(x)):
                       this.combinations_.apply(i - 1, ls.tail())
                               .map( x-> list(ls.head()).concat(x) );


     /*
     *    Question 27
     *    Group the elements of a set into disjoint subsets.
     */
    //    9 elements with 2, 3, 4
    public List<List<List<T>>> group3(List<T> ls){
        return this.combinations(2, ls)
                .flatMap(
                    x->  this.combinations(3, List.diff(ls, x)).map(
                                    y-> list(x, y, List.diff(List.diff(ls, x), y))
                    )
                );
    }


    public BiFunction<List<Integer>, List<T>, List<List<List<T>>>> group =
            (intLs, ls) -> intLs.isEmpty()?
                    NIL:
                    this.combinations(intLs.head(), ls).flatMap(
                            x -> this.group.apply(intLs.tail(), List.diff(ls, x)).map(
                                    y-> list(x).concat(y)
                            )

                    );

    /*
     *    Question 28
     *    Sorting a list of lists according to length of sublists.
     */
    public Function<List<List<T>>, List<List<T>>> lsort =
            ls -> ls.map( l-> new Tuple<>(l.getLength(), l) )
                    .foldRight(  NIL, (t, acc)-> this.insert.apply(t, acc));



    private BiFunction<Tuple<Integer, List<T>>, List<List<T>> , List<List<T>>> insert =
            (t, ls) ->
                    ls.isEmpty()?
                            list(t._2):
                            ls.head().getLength() > t._1 ?
                                    list(t._2).concat(ls):
                                    list(ls.head()).concat(this.insert.apply(t, ls.tail()));





}
