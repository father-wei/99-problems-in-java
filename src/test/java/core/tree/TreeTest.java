package core.tree;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static core.tree.Tree.*;

public class TreeTest {
    @Test
    public void testInsert(){
        Tree<Integer> tree = empty();
        Tree<Integer> insertOne = tree.insert(1);
        assertEquals("(NIL 1 NIL)", insertOne.toString());

        Tree<Integer> insertTwo = insertOne.insert(2);
        assertEquals("(NIL 1 (NIL 2 NIL))", insertTwo.toString());

        Tree<Integer> insertZero = insertTwo.insert(0);
        assertEquals("((NIL 0 NIL) 1 (NIL 2 NIL))", insertZero.toString());
    }

    
}
