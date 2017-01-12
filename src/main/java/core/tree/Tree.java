package core.tree;


public abstract class Tree<T extends Comparable<T>> {

    public abstract T value();

    @SuppressWarnings("rawtypes")
    private static Tree EMPTY = new Empty();

    abstract Tree<T> left();
    abstract Tree<T> right();

    public abstract Tree<T> insert(T t);

    private static class Empty<T extends Comparable<T>> extends Tree<T>{
        @Override
        public T value(){
            throw new IllegalStateException("value() called on empty");
        }

        @Override
        Tree<T> left(){
            throw new IllegalStateException("left() called on empty");
        }

        @Override
        Tree<T> right(){
            throw new IllegalStateException("right() called on empty");
        }

        @Override
        public String toString(){
            return "NIL";
        }

        @Override
        public Tree<T> insert(T value){
            return new Branch<>(EMPTY, value, EMPTY);
        }
    }

    private static class Branch<T extends Comparable<T>> extends Tree<T>{

        final private T value;
        private final Tree<T> left;
        private final Tree<T> right;

        private Branch(Tree<T> left, T value, Tree<T> right){
            this.left = left;
            this.value = value;
            this.right = right;
        }

        @Override
        public T value(){
            return value;
        }

        @Override
        Tree<T> left(){
            return left;
        }

        @Override
        Tree<T> right(){
            return right;
        }

        @Override
        public String toString(){
            return String.format("(%s %s %s)", left, value, right);
        }

        @Override
        public Tree<T> insert(T t){
            return t.compareTo(this.value()) < 0?
                     new Branch<>(left.insert(t), this.value, right) :
                     t.compareTo(this.value()) > 0?
                             new Branch<>(left, this.value, right.insert(t)):
                             new Branch<>(this.left, t, this.right);
        }

    }


    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Tree<T> empty(){
        return EMPTY;
    }
}
