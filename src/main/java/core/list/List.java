package core.list;


import static core.tailcall.TailCall.*;

import core.common.Result;
import core.common.Tuple;
import core.tailcall.TailCall;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import static core.common.Case.*;
import static core.tailcall.TailCall.ret;

public abstract class List<T> {

    public abstract T head();

    public abstract List<T> tail();

    public abstract boolean isEmpty();

    public abstract int getLength();


    @Override
    public String toString(){
        return String.format("[%s, Nil]", toString_("").eval());
    }

    private TailCall<String> toString_(String acc){
        return this.isEmpty() ?
                ret (acc) :
                sus (() -> this.tail().toString_(
                        acc == "" ?
                                this.head().toString() :
                                String.format("%s, %s", acc, this.head())));

    }

    public static final List NIL = new Nil();


    public static <A> List<A> list() {
        return NIL;
    }

    public static <A> List<A> list(A... a) {

        List<A> n = list();

        for (int i = a.length - 1; i >= 0; i--) {
            n = new Cons<>(a[i], n);
        }

        return n;
    }




    private List(){}

    private static class Nil<T> extends List<T> {

        public T head() {
            throw new IllegalStateException("head called en empty list");
        }

        public List<T> tail() {
            throw new IllegalStateException("head called en empty list");
        }

        public boolean isEmpty() {
            return true;
        }

        public int getLength(){
            return 0;
        }

    }

    private static class Cons<T> extends List<T> {

        private final T head;
        private final List<T> tail;
        private final int length;

        private Cons(T head, List<T> tail){
            this.head = head;
            this.tail = tail;
            this.length = tail.getLength() + 1;
        }

        public T head(){
            return head;
        }

        public List<T> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public int getLength(){
            return this.length;
        }

    }


    // not a tailcall, unsafe
   /* public <U> U foldRight(U acc, BiFunction<U, T, U> f) {
        return this.isEmpty()?
                acc :
                f.apply(this.tail().foldRight(acc, f), this.head());
    }*/


   // tailcall, safe
    public <U> U foldRight(U acc, BiFunction<T, U, U> f){
        return this.reverse().foldLeft(acc, (u, t) -> f.apply(t, u));
    }



    // tailcall, safe
    public <U> U foldLeft(U acc, BiFunction<U, T, U> f) {
        return foldLeft_(acc, f).eval();
    }


    private  <U> TailCall<U> foldLeft_(U acc, BiFunction<U, T, U> f) {
        return this.isEmpty()?
                ret(acc) :
                sus(()-> this.tail().foldLeft_(f.apply(acc, this.head()), f));
    }


    public List<T> reverse(){
        return this.foldLeft(NIL, (acc, head) -> new Cons<>(head, acc));
    }

    public List<T> concat(List<T> ls){
        return this.foldRight(ls, (head, acc) -> new Cons<>(head,acc));
    }

    public <U> List<U> map (Function<T, U> f) {
        return this.foldRight(NIL, (t, acc) -> new Cons<>(f.apply(t), acc));
    }


    public <U> List<U> flatMap(Function<T, List<U>> f) {
        return this.foldRight(NIL,
                                (t, acc) -> f.apply(t)
                                             .concat(this.tail().flatMap(f)));
    }

    public Tuple<List<T>, List<T>> span (){
        return span_(this, new Tuple<List<T>, List<T>>(list(), list())).eval();
    }

    private TailCall<Tuple<List<T>, List<T>>> span_ (List<T> ls, Tuple<List<T>, List<T>> acc){

        return ls.isEmpty() ?
                    ret (acc) :
                        acc._1.isEmpty() ?
                            sus (() -> span_ (ls.tail(), new Tuple<>(acc._1.concat(list(ls.head())), acc._2 ))) :
                                    acc._1.head().equals(ls.head()) ?
                                        sus (() -> span_ (ls.tail(), new Tuple<>(acc._1.concat(list(ls.head())), acc._2 ))) :
                                        sus (() -> span_ (NIL, new Tuple<>(acc._1, ls)));

    }

    @Override
    public boolean equals(Object o){
        return this == o?
                    true :
                    o == null?
                         false :
                         getClass() != o.getClass()?
                                 false:
                                 equals.apply(this, (List<T>) o).getOrElse(false);

    }

    private BiFunction<List<T>, List<T>, Result<Boolean>> equals =
            (ls1, ls2) -> match
            (
                mCase(Result.success(false)),
                mCase(()-> ls1.isEmpty() && ls2.isEmpty(),    ()-> Result.success(true)),
                mCase(()-> (ls1.isEmpty() && !ls2.isEmpty())  || (!ls1.isEmpty() && ls2.isEmpty()),  ()-> Result.success(false)),
                mCase(()-> ls1.head().equals(ls2.head()),     ()-> this.equals.apply(ls1.tail(), ls2.tail()))
            );


}