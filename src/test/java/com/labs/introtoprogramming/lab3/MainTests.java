package com.labs.introtoprogramming.lab3;

import com.labs.introtoprogramming.lab3.datastructures.MyHashTable;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Dictionary;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class MainTests {
  private static final String EXPECTED_INPUT = "A\n"
          + "Def: something\n"
          + "B\n"
          + "Def: else";

  private static final String EMPTY_INPUT = "";

  private static final String EMPTY_DEFINITION_INPUT = "A\n";

  private static final String EQUAL_WORDS_INPUT = "A\n"
          + "Def: something\n"
          + "A\n"
          + "Def: else";

  private static final ArrayList<String> DUMMY_LINES = new ArrayList<String>() {{
    add("UPPER");
    add("lower");
    add("UoPeR");
    add("");
    add("1.$/");
  }};

  @Test
  public void testLoadToDictionary() throws IOException {
    MyHashTable<String, String> dict = new MyHashTable<>(100);
    Main.loadToDictionary(
            new ByteArrayInputStream(EXPECTED_INPUT.getBytes(StandardCharsets.UTF_8)),
            dict
    );
    assertEquals(2, dict.getSize());
    assertEquals("\nDef: something", dict.getOrDefault("A", null));
    assertEquals("\nDef: else", dict.getOrDefault("B", null));
  }

  @Test
  public void testLoadToDictionaryEmptyInput() throws IOException {
    MyHashTable<String, String> dict = new MyHashTable<>(100);
    Main.loadToDictionary(
            new ByteArrayInputStream(EMPTY_INPUT.getBytes(StandardCharsets.UTF_8)),
            dict
    );
    assertEquals(0, dict.getSize());
  }

  @Test
  public void testLoadToDictionaryEmptyDefinition() throws IOException {
    MyHashTable<String, String> dict = new MyHashTable<>(100);
    Main.loadToDictionary(
            new ByteArrayInputStream(EMPTY_DEFINITION_INPUT.getBytes(StandardCharsets.UTF_8)),
            dict
    );
    assertEquals(0, dict.getSize());
  }

  @Test
  public void testLoadToDictionaryEqualWords() throws IOException {
    MyHashTable<String, String> dict = new MyHashTable<>(100);
    Main.loadToDictionary(
            new ByteArrayInputStream(EQUAL_WORDS_INPUT.getBytes(StandardCharsets.UTF_8)),
            dict
    );
    assertEquals(1, dict.getSize());
    assertEquals("\nDef: something\n" + Main.DELIMITER + "\nDef: else", dict.getOrDefault("A", null));
  }

  @Test
  public void testAddToDictionary() {
    MyHashTable<String, String> dict = new MyHashTable<>();
    Main.addToDictionary("A", new StringBuilder("1"), dict);
    assertEquals(1, dict.getSize());
    assertEquals("1", dict.getOrDefault("A", ""));
  }

  @Test
  public void testAddToDictionaryEmptyToken() {
    MyHashTable<String, String> dict = new MyHashTable<>();
    Main.addToDictionary("", new StringBuilder("2"), dict);
    assertEquals(0, dict.getSize());
  }

  @Test
  public void testAddToDictionaryEmptyContent() {
    MyHashTable<String, String> dict = new MyHashTable<>();
    Main.addToDictionary("B", new StringBuilder(), dict);
    assertEquals(0, dict.getSize());
  }

  @Test
  public void testAddToDictionarySameToken() {
    MyHashTable<String, String> dict = new MyHashTable<>();
    Main.addToDictionary("A", new StringBuilder("1"), dict);
    Main.addToDictionary("A", new StringBuilder("2"), dict);
    assertEquals(1, dict.getSize());
    assertEquals("1\n" + Main.DELIMITER  +"2", dict.getOrDefault("A", ""));
  }

  @Test
  public void testIsLineUpperCase() {
    boolean res = Main.isLineUpperCase(DUMMY_LINES.get(0));
    assertTrue(res);
  }

  @Test
  public void testIsLineUpperCaseLowerCase() {
    boolean res = Main.isLineUpperCase(DUMMY_LINES.get(1));
    assertFalse(res);
  }

  @Test
  public void testIsLineUpperCaseMixed() {
    boolean res = Main.isLineUpperCase(DUMMY_LINES.get(2));
    assertFalse(res);
  }

  @Test
  public void testIsLineUpperCaseEmpty() {
    boolean res = Main.isLineUpperCase(DUMMY_LINES.get(3));
    assertFalse(res);
  }

  @Test
  public void testIsLineUpperCaseNotAlpha() {
    boolean res = Main.isLineUpperCase(DUMMY_LINES.get(4));
    assertTrue(res);
  }
}
