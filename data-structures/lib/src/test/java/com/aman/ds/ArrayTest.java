package com.aman.ds;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



class ArrayTest {
    @Test void testEmptyArray() {
        Array<Integer> array = new Array<>();
        assertTrue(array.isEmpty());
    }

    @Test void testRemovingEmpty() {
        Array<Integer> array = new Array<>();
        assertThrows(Exception.class, () -> 
                array.removeAt(0));
    }

    @Test void testIndexOutOfBound() {
        Array<Integer> array = new Array<>();
        array.add(4);
        array.add(5);
        assertThrows(IndexOutOfBoundsException.class, () -> 
                array.removeAt(2));
    }

    @Test void testIndexOutOfBoundWithNegativeIndex() {
        Array<Integer> array = new Array<>();
        array.add(3);
        assertThrows(IndexOutOfBoundsException.class, () ->
                array.removeAt(-1));
    }

} 
