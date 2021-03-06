package core.common;

import java.util.function.Supplier;


public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    private Case(Supplier<Boolean> supplierBoolean, Supplier<Result<T>> supplierResult) {
        super(supplierBoolean, supplierResult);
    }

    // create default case class for match function literal array arguments
    private static class DefaultCase<T> extends Case<T> {
        private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result<T>> resultSupplier) {
            super(booleanSupplier, resultSupplier);
        }
    }

    //factory method for case
    public static <T> Case <T> mCase(Supplier<Boolean> condition, Supplier<Result<T>> value) {
        return new Case<>(condition, value);
    }

    //factory method for default case
    public static <T> Case <T> mCase(Result<T> value) {
        return new DefaultCase<T>(()-> true, ()-> value);
    }


    public static <T> Result<T> match (Case<T> defaultCase, Case<T> ...matchers){

        for(Case<T> aCase : matchers ){
            if(aCase._1.get()) return aCase._2.get();
        }

        return defaultCase._2.get();
    }


}
