package problems.prolog.lists;

import com.sun.org.apache.regexp.internal.RE;
import core.common.Result;
import core.list.List;

import java.util.function.BiFunction;
import java.util.function.Function;
import static core.common.Case.*;


public class Questions1To28<T> {

    /*
     *    Question 1
     *    Find the last element of a list
     */
    public Function<List<T>, Result<T>>
            last = ls -> match
            (
                mCase(Result.failure("Can not find the last element from the List")),
                mCase(()-> ls.isEmpty(),          () -> Result.failure("The list is empty")),
                mCase(()-> ls.tail().isEmpty(),   () -> Result.success(ls.head())),
                mCase(()-> !ls.tail().isEmpty(),  () -> this.last.apply(ls.tail()))
            );


    /*
     *    Question 2
     *    Find the last but one element of a list.
     */
    public Function<List<T>, Result<T>>
            lastButOne = ls -> match
            (
                mCase(Result.failure("Can not find the last but one element from the List")),
                mCase(()-> ls.isEmpty() || ls.tail().isEmpty(),          () -> Result.failure("The list is empty or only contains one element")),
                mCase(()-> ls.tail().tail().isEmpty(),      () -> Result.success(ls.head())),
                mCase(()-> !ls.tail().tail().isEmpty(),     () -> this.lastButOne.apply(ls.tail()))
            );



    /*
     *    Question 3
     *    Find the Kth element of a list.
     */
    public BiFunction<List<T>, Integer, Result<T>>
            kthEle = (ls, n) -> match
            (
                mCase(Result.failure("Can not find the kth element from the List")),
                mCase(() -> ls.isEmpty(),   ()-> Result.failure("Exceed the boundary")),
                mCase(() -> n  <  0,        () -> Result.failure("Input number cannot be navigate")),
                mCase(() -> n  == 0,        () -> Result.success(ls.head())),
                mCase(() -> n  >  0,        () -> this.kthEle.apply(ls.tail(), n - 1))
            );

    /*
     *    Question 4
     *    Find the number of elements of a list.
     */
    public Function<List<T>, Integer> length = ls -> ls.getLength();


    /*
     *    Question 5
     *    Reverse a list.
     */
    public Function<List<T>, List<T>> reverse = ls -> ls.reverse();


    /*
     *    Question 6
     *    Find out whether a list is a palindrome.
     */
    public Function<List<T>, Boolean> isPalindrome = ls -> ls.equals(ls.reverse());


}
