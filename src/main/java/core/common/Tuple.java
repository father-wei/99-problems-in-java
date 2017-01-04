package core.common;

/**
 * Created by bwei3 on 1/4/17.
 */
public class Tuple<T, U> {

    private final T first;

    private final U second;

    public Tuple (T first, U second){
        this.first  =  first;
        this.second = second;
    }

    public T _1() {
        return this.first;
    }

    public U _2() {
        return this.second;
    }
}
