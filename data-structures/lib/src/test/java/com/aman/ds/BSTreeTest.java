package com.aman.ds;


import com.aman.ds.BSTree;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BSTreeTest {
    BSTree<Integer> bst;

    @BeforeEach
    void createEmptyTree() {
        this.bst = new BSTree<Integer>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(bst.isEmpty());
        bst.add(5);
        assertFalse(bst.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(bst.size(), 0);
        bst.add(5);
        assertEquals(bst.size(), 1);
    }

    @Test
    void testAdd() {
        assertTrue(bst.add(4));
        assertFalse(bst.add(4));
    }

    @Test
    void testContains() {
        new Random().ints(0,10).limit(10)
            .forEach(e -> bst.add(e));
        bst.forEach(e -> assertTrue(bst.contains(e)));
    }

    @Test
    void testRemove() {
        bst.add(5);
        bst.add(-5);
        bst.add(0);
        assertTrue(bst.remove(5));
        assertFalse(bst.contains(5));
        assertFalse(bst.remove(5));
        bst.remove(0);
        bst.remove(-5);
        assertTrue(bst.isEmpty());
    }

    @Test
    void testHeight() {
        assertEquals(bst.height(), 0);
        bst.add(4);
        assertEquals(bst.height(), 1);
        bst.add(6);
        bst.add(2);
        assertEquals(bst.height(), 2);
        bst.add(3);
        assertEquals(bst.height(), 3);

    }
}
