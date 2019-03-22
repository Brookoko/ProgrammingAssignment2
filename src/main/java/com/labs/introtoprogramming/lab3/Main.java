package com.labs.introtoprogramming.lab3;

import com.labs.introtoprogramming.lab3.datastructures.MyHashTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
  private static MyHashTable<String, String> dictionary;
  private static final String DELIMITER = "----------------------------------------------------------------";
  private static final String INPUT_FILE_NAME = "dict_processed.txt";
  private static final int DICTIONARY_CAPACITY= 100000;

  public static void main(String[] args) {
    dictionary = new MyHashTable<>(DICTIONARY_CAPACITY);
    File file = new File(INPUT_FILE_NAME);
    loadToDictionary(file, dictionary);
  }

  /**
   * Load to dictionary from file
   *
   * @param file file with words and their definition (txt)
   * @param dict hast table to which load words
   */
  private static void loadToDictionary(File file, MyHashTable<String, String> dict) {
    try {
      loadToDictionary(new FileInputStream(file), dict);
    } catch (IOException e) {
      System.err.println("Failed to read file");
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Load to dictionary from txt data input stream
   *
   * @param in input stream with words definition data
   * @param dict hash table to which load words
   * @throws IOException if stream read failed
   */
  private static void loadToDictionary(InputStream in, MyHashTable<String, String> dict) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    String line;
    String token = "";
    StringBuilder content = new StringBuilder();
    while ((line = bf.readLine()) != null) {
      if (isLineUpperCase(line)) {
        if (!token.isEmpty() && content.length() != 0) {
          if (dict.containsKey(token)) {
            content.insert(0, DELIMITER)
                    .insert(0, "\n")
                    .insert(0, dict.get(token));
          }
          dict.put(token, content.toString());
        }
        token = line;
        content.setLength(0);
      } else if (!line.isEmpty()) {
        content.append("\n")
                .append(line);
      }
    }
    bf.close();
  }

  /**
   * Check is string consists only of uppercase characters
   *
   * @param line string to be checked
   * @return true if string consist only of uppercase characters
   */
  private static boolean isLineUpperCase(String line) {
    if (line.isEmpty()) return false;
    for (char c : line.toCharArray()) {
      if (Character.isLowerCase(c)) return false;
    }
    return true;
  }
}
