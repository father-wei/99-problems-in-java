package core.List;


import static core.tailcall.TailCall.*;
import core.tailcall.TailCall;

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


}