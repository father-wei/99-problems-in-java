package core.list;


import static core.tailcall.TailCall.*;
import core.tailcall.TailCall;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class List<T> {

    public abstract T head();

    public abstract List<T> tail();

    public abstract boolean isEmpty();


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

    }

    private static class Cons<T> extends List<T> {

        private final T head;

        private final List<T> tail;

        private Cons(T head, List<T> tail){
            this.head = head;
            this.tail = tail;
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
}