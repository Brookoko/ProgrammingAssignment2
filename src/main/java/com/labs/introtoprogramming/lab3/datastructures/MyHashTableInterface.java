package com.labs.introtoprogramming.lab3.datastructures;

import java.util.List;
import java.util.Optional;

/**
 * Data structure which stores key-value pairs.
 * Cannot contain duplicate keys, each key maps to one value.
 *
 * @param <K> the type of keys in this hash table.
 * @param <V> the type of values in this hash table.
 */
public interface MyHashTableInterface<K, V> {

  /**
   * Sets value for the key.
   *
   * @param key key to set value for
   * @param value value to set
   */
  public void put(K key, V value);

  /**
   * Get value by key, or empty optional if no such key found in this hash table.
   *
   * @param key key to get value for
   * @return value for this key
   */
  public List<V> get(K key);

  /**
   * Get value by key, or default value of no such key found in this hash table.
   *
   * @param key ket to get value for
   * @param defaultValue value to return if key does not exist in this hash table
   * @return value for this key
   */
  public List<V> getOrDefault(K key, List<V> defaultValue);

  /**
   * Remove key from hash table.
   *
   * @param key key to remove
   */
  public void remove(K key);

  /** Returns <tt>true</tt> if this hash table contains value for specific key. */
  public boolean containsKey(K key);

  /** Remove all values from this hash table. */
  public void clear();

  /** Returns <tt>true</tt> if this hash table contains no elements. */
  boolean isEmpty();

  /** Returns the number of values in this hash table. */
  int getSize();

}
