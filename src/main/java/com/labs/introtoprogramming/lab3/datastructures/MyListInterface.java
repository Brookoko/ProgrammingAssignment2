package com.labs.introtoprogramming.lab3.datastructures;

import java.util.Optional;

/**
 * An ordered sequence of elements of variable size.
 * Adding or removing elements is possible by their integer index
 * representing position in the list.
 *
 * @param <T> the type of elements in this list
 *
 * @author Nikita Volobuev
 */
public interface MyListInterface<T> extends Iterable<T> {

  /**
   * Inserts the element at the end of the list.
   *
   * @param element element to be inserted
   */
  public void add(T element);

  /**
   * Inserts the element to the specified position in the list.
   *
   * @param element element to be inserted
   * @param index position to insert to
   * @throws IndexOutOfBoundsException if index is out of bounds of this list
   */
  public void add(int index, T element);

  /**
   * Replace the element at index <tt>index</tt> with <tt>element</tt>.
   *
   * @param element element to set
   * @param index index to set to
   * @throws IndexOutOfBoundsException if index is out of bounds of this list
   */
  public void set(int index, T element);

  /**
   * Get element with specified index from this list.
   *
   * @param index index of element to get
   * @return element
   * @throws IndexOutOfBoundsException if index is out of bounds of this list
   */
  public T get(int index);

  /**
   * Find element in the list and removeElement it.
   *
   * @param element element to be removed
   * @throws java.util.NoSuchElementException if element is not found
   */
  public void removeElement(T element);

  /**
   * Remove element at specified index.
   *
   * @param index index of element to be removed
   * @throws IndexOutOfBoundsException if index is out of bounds of this list
   */
  public void removeElementByIndex(int index);

  /**
   * Find element in this list.
   * More formally, find element e, so that <tt>e.equals(element)</tt> is true.
   *
   * @param element element to find
   * @return element index wrapped in Optional, or empty Optional if element is not found
   */
  public Optional<Integer> indexOf(T element);

  /** Remove all elements from this list. */
  public void clear();

  /** Returns the number of elements in this list. */
  public int getSize();

  /** Returns <tt>true</tt> if this list contains no elements. */
  public boolean isEmpty();

}
