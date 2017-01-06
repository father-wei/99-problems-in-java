package core.common;

/**
 * Created by bwei3 on 1/4/17.
 */
public class Tuple<T, U> {

    public final T _1;

    public final U _2;

    public Tuple (T first, U second){
        this._1  =  first;
        this._2 = second;
    }

    @Override
    public String toString(){
        return String.format("(%s, %s)", _1, _2);
    }
}
