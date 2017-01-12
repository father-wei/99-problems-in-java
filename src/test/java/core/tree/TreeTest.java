package core.tree;
import org.junit.Test;

import static core.list.List.*;
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

    @Test
    public void testMember(){
        Tree<Integer> empty = empty();
        Tree<Integer> tree = empty.insert(1).insert(2).insert(3);
        assertEquals(true, tree.member(1));
        assertEquals(true, tree.member(2));
        assertEquals(true, tree.member(3));
        assertEquals(false, tree.member(4));
    }

    @Test
    public void testFactory(){
        Tree<Integer> tree = tree(1, 2, 3);
        assertEquals("(NIL 1 (NIL 2 (NIL 3 NIL)))", tree.toString());

        Tree<Integer> tree2 = tree(list(1, 2, 3));
        assertEquals("(NIL 1 (NIL 2 (NIL 3 NIL)))", tree2.toString());

    }

    @Test
    public void testSize(){
        Tree<Integer> tree = tree(1, 2, 3);
        assertEquals(3, tree.size());

        Tree<Integer> tree2 = tree(1, 2, 3, 0, -1);
        assertEquals(5, tree2.size());

        // like tree set, not save duplicated value
        Tree<Integer> tree3 = tree(1,2,1);
        assertEquals(2, tree3.size());
    }

    @Test
    public void testHeight(){
        Tree<Integer> tree = tree(1, 2, 3);
        assertEquals(2, tree.height());

        Tree<Integer> tree2 = tree(1, 2, 3, 0, -1);
        assertEquals(2, tree2.height());

    }

}
