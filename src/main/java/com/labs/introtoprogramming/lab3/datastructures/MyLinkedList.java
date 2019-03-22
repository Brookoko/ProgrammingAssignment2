package com.labs.introtoprogramming.lab3.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Linked list implementation.
 *
 * @param <T> the type of elements in this list
 *
 * @author Nikita Volobuev
 * @see MyListInterface
 */
public class MyLinkedList<T> implements MyListInterface<T> {

  private class MyLinkedListNode<E> {
    private E element;
    private MyLinkedListNode<E> next;

    private MyLinkedListNode(E element, MyLinkedListNode<E> next) {
      this.element = element;
      this.next = next;
    }
  }

  private MyLinkedListNode<T> front = null;
  private int size = 0;

  @Override
  public void add(T element) {
    add(element, size);
  }

  @Override
  public void add(T element, int index) {
    MyLinkedListNode<T> node = new MyLinkedListNode<>(element, null);

    size++;

    if (front == null || index == size - 1) {
      node.next = front;
      front = node;
      return;
    }

    MyLinkedListNode<T> cursor = getNode(index + 1);

    node.next = cursor.next;
    cursor.next = node;
  }

  @Override
  public void set(T element, int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    MyLinkedListNode<T> cursor = front;
    for (int i = 0; i < size - index - 1; i++) {
      cursor = cursor.next;
    }

    cursor.element = element;
  }

  /**
   * Get linked list node by index.
   *
   * @param index index of node to get
   * @throws IndexOutOfBoundsException if specified index is out of list bounds
   * @return list node
   */
  private MyLinkedListNode<T> getNode(int index) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    MyLinkedListNode<T> cursor = front;
    for (int i = 0; i < size - index - 1; i++) {
      cursor = cursor.next;
    }

    return cursor;
  }

  @Override
  public T get(int index) {
    return getNode(index).element;
  }

  @Override
  public void remove(T element) {
    remove(indexOf(element).orElseThrow(NoSuchElementException::new));
  }

  @Override
  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    MyLinkedListNode<T> prev = null;
    MyLinkedListNode<T> cursor = front;
    for (int i = 0; i < size - index - 1; i++) {
      prev = cursor;
      cursor = cursor.next;
    }

    size--;

    if (cursor == front) {
      front = front.next;
      return;
    }

    prev.next = cursor.next;
  }

  @Override
  public Optional<Integer> indexOf(T element) {
    MyLinkedListNode<T> cursor = front;

    for (int i = 0; i < size; i++) {
      if (cursor.element.equals(element)) {
        return Optional.of(size - i - 1);
      }
      cursor = cursor.next;
    }

    return Optional.empty();
  }

  @Override
  public void clear() {
    front = null;
    size = 0;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return getSize() == 0;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      MyLinkedListNode<T> cursor = front;

      @Override
      public boolean hasNext() {
        return cursor != null;
      }

      @Override
      public T next() {
        MyLinkedListNode<T> current = cursor;
        cursor = cursor.next;
        return current.element;
      }
    };
  }
}
