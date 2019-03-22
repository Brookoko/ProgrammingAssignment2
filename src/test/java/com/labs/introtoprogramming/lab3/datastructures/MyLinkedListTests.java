package com.labs.introtoprogramming.lab3.datastructures;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.Test;

public class MyLinkedListTests {

  @Test
  public void testAdd() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    assertEquals(0, list.getSize());
    list.add(42);
    assertEquals(1, list.getSize());
    list.add(-1);
    assertEquals(2, list.getSize());

    assertEquals(42, list.get(0).intValue());
    assertEquals(-1, list.get(1).intValue());
  }

  @Test
  public void testAddByIndex() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(42, 0);
    list.add(-1, 0);
    list.add(0, 2);
    list.add(1, 1);
    assertEquals(4, list.getSize());

    assertEquals(-1, list.get(0).intValue());
    assertEquals(1, list.get(1).intValue());
    assertEquals(42, list.get(2).intValue());
    assertEquals(0, list.get(3).intValue());
  }

  @Test
  public void testSet() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(42, 0);
    assertEquals(42, list.get(0).intValue());
    list.set(10, 0);
    assertEquals(10, list.get(0).intValue());

    list.add(2);
    list.add(3);

    assertEquals(2, list.get(1).intValue());
    list.set(7, 1);
    list.set(8, 2);
    assertEquals(7, list.get(1).intValue());
    assertEquals(8, list.get(2).intValue());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testSetOutOfBounds() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(42, 0);
    assertEquals(1, list.getSize());
    list.set(1, 1000);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetOutOfBounds() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(42);
    assertEquals(1, list.getSize());
    list.get(1000);
  }

  @Test
  public void testRemove() {
    MyLinkedList<String> list = new MyLinkedList<>();

    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    assertEquals(4, list.getSize());

    list.remove("c");
    assertEquals(3, list.getSize());

    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
    assertEquals("d", list.get(2));
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveElementNotFound() {
    MyLinkedList<String> list = new MyLinkedList<>();

    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    assertEquals(4, list.getSize());

    list.remove("e");
  }

  @Test
  public void testRemoveByIndex() {
    MyLinkedList<String> list = new MyLinkedList<>();

    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    assertEquals(4, list.getSize());

    list.remove(2);
    assertEquals(3, list.getSize());

    assertEquals("a", list.get(0));
    assertEquals("b", list.get(1));
    assertEquals("d", list.get(2));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testRemoveIndexOutOfBounds() {
    MyLinkedList<String> list = new MyLinkedList<>();

    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    assertEquals(4, list.getSize());

    list.remove(1000);
  }

  @Test
  public void testRemoveOnlyElement() {
    MyLinkedList<String> list = new MyLinkedList<>();

    list.add("foobar");
    assertEquals(1, list.getSize());

    list.remove(0);
    assertEquals(0, list.getSize());
  }

  @Test
  public void testClear() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(1);
    list.add(2);
    assertEquals(2, list.getSize());

    list.clear();
    assertEquals(0, list.getSize());
    assertTrue(list.isEmpty());
  }

  @Test
  public void testListIterate() {
    MyLinkedList<Integer> list = new MyLinkedList<>();

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);

    int[] result = new int[4];
    int i = 0;
    for (Integer n : list) {
      result[i] = n;
      i++;
    }
    assertArrayEquals(new int[]{4, 3, 2, 1}, result);
  }

}
