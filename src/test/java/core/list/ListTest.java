package core.list;

import org.junit.Test;
import static core.list.List.*;
import static org.junit.Assert.assertEquals;


public class ListTest {
    @Test
    public void testToString() {
        assertEquals("[1, 2, 3, Nil]", list(1, 2, 3).toString());
    }

    @Test
    public void testHead() {
        assertEquals(Integer.valueOf(1) , list(1, 2, 3).head());
    }

    @Test
    public void testTail(){
        assertEquals( "[2, 3, Nil]" , list(1, 2, 3).tail().toString());
    }

    @Test
    public void testFoldRight() {
        int sum = list(1,2,3).foldRight(0, (x, y) -> x + y);
        assertEquals(6, sum);
    }

    @Test
    public void testFoldLeft(){
        int sum = list(1,2,3).foldLeft(0, (x, y) -> x + y);
        assertEquals(6, sum);
    }

    @Test
    public void testReverse(){
        assertEquals("[3, 2, 1, Nil]", list(1,2,3).reverse().toString());
    }

    @Test
    public void testConcat(){
        assertEquals("[1, 2, 3, 4, Nil]", list(1,2).concat(list(3,4)).toString());
    }

    @Test
    public void testMap(){
        List<Integer> ls = list(1,2,3).map( x -> x +1 );
        assertEquals(Integer.valueOf(2),  ls.head());
        assertEquals(Integer.valueOf(3),  ls.tail().head());
        assertEquals(Integer.valueOf(4),  ls.tail().tail().head());
        assertEquals(true,  ls.tail().tail().tail().isEmpty());

        assertEquals("[2, 3, 4, Nil]", ls.toString());
    }

    @Test
    public void testFlatMap(){
        List<Integer> ls1 = list(1, 2, 3).flatMap(x -> list(x + 2) );
        assertEquals("[3, 4, 5, Nil]", ls1.toString());

        List<Integer> ls2 =
                list(2, 3, 4)
                            .flatMap(
                                x -> list(1, 2, 3)
                                        .map( y -> x + y));

        assertEquals("[3, 4, 5, 4, 5, 6, 5, 6, 7, Nil]", ls2.toString());

    }

    @Test
    public void testGetLength(){
        assertEquals(5, list(1,2,3,4,5).getLength());
    }


    @Test
    public void testEquals(){
        assertEquals(true, list().equals(list()));
        assertEquals(true, list(1).equals(list(1)));
        assertEquals(false, list(1, 2).equals(list(1)));
    }


}
