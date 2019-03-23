package com.labs.introtoprogramming.lab3;

import com.labs.introtoprogramming.lab3.datastructures.MyHashTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Main {
  static final String DELIMITER =
          "----------------------------------------------------------------";
  private static final String INPUT_FILE_NAME = "dict_processed.txt";
  private static final int DICTIONARY_CAPACITY = 100;
  private static final String TOKEN_TO_LEAVE = ":q";

  /**
   * This starts application.
   * Input file is read and parse. Then words definition load to hash table
   * and user can find them via standard input/output stream
   */
  public static void main(String[] args) {
    MyHashTable<String, String> dictionary = new MyHashTable<>(DICTIONARY_CAPACITY);
    File file = new File(INPUT_FILE_NAME);
    loadToDictionary(file, dictionary);
    processUserInput(dictionary);
    
    MyHashTable<String, Integer> testTable = new MyHashTable<>();
    long res = benchmark(testTable, 1000000);
    System.out.println(res);
  }

  /**
   * Load to dictionary from file.
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
   * Load to dictionary from txt data input stream.
   *
   * @param in input stream with words definition data
   * @param dict hash table to which load words
   * @throws IOException if stream read failed
   */
  static void loadToDictionary(
          InputStream in,
          MyHashTable<String, String> dict
  ) throws IOException {
    BufferedReader bf = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
    String line;
    String token = "";
    StringBuilder content = new StringBuilder();
    while ((line = bf.readLine()) != null) {
      if (isLineUpperCase(line)) {
        addToDictionary(token, content, dict);
        token = line;
        content.setLength(0);
      } else if (!line.isEmpty()) {
        content.append("\n")
                .append(line);
      }
    }
    addToDictionary(token, content, dict);
    bf.close();
  }

  /**
   * Add new definition to dictionary.
   *
   * @param token word to add
   * @param content definition of word
   * @param dict dictionary to add
   */
  static void addToDictionary(
          String token,
          StringBuilder content,
          MyHashTable<String,String> dict
  ) {
    if (!token.isEmpty() && content.length() != 0) {
      if (dict.containsKey(token)) {
        content.insert(0, DELIMITER)
                .insert(0, "\n")
                .insert(0, dict.getOrDefault(token, ""));
        dict.remove(token);
      }
      dict.put(token, content.toString());
    }
  }

  /**
   * Check is string consists only of uppercase characters.
   *
   * @param line string to be checked
   * @return true if string consist only of uppercase characters
   */
  static boolean isLineUpperCase(String line) {
    if (line.isEmpty()) {
      return false;
    }
    for (char c : line.toCharArray()) {
      if (Character.isLowerCase(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Process user input to get word definition from dictionary.
   * Input is read from System.in
   *
   * @param dict word definition dictionary to use
   */
  private static void processUserInput(MyHashTable<String, String> dict) {
    processUserInput(dict, System.in, System.out, System.out);
  }

  /**
   * Process user input to get word definition from dictionary.
   *
   * @param dict word definition dictionary to use
   * @param in input stream to read user input from
   * @param msgOut print stream to print messages to user
   * @param defOut print stream to print definitions to
   */
  static void processUserInput(
          MyHashTable<String, String> dict,
          InputStream in,
          PrintStream msgOut,
          PrintStream defOut
  ) {
    Scanner scanner = new Scanner(new InputStreamReader(in, StandardCharsets.UTF_8));
    msgOut.println("Print :q to leave");
    msgOut.print("Type a sentence to get definition: ");

    printWordDefs(scanner.nextLine().split(" "), dict, defOut);

    msgOut.print("Type a word to get definition: ");
    while (scanner.hasNextLine()) {
      String token = scanner.nextLine();

      if (token.equals(TOKEN_TO_LEAVE)) {
        return;
      }

      Optional<String> def = findWordInDictionary(token, dict);
      if (!def.isPresent()) {
        defOut.printf("Sorry %s cannot be found%n", token);
      } else {
        defOut.printf("%s; %s%n", token, def.get());
      }
      msgOut.print("Type a word to get definition: ");
    }
  }

  /**
   * Print definition for each word in sentence.
   *
   * @param sentence array of words
   * @param dict dictionary of defs to use
   * @param defOut print stream to print defs to
   */
  private static void printWordDefs(
          String[] sentence,
          MyHashTable<String, String> dict,
          PrintStream defOut
  ) {
    Arrays.stream(sentence).forEach(word -> {
      Optional<String> def = findWordInDictionary(word, dict);
      if (!def.isPresent()) {
        defOut.printf("%s; not found%n", word);
        return;
      }

      defOut.printf("%s; %s%n", word, def.get());
    });
  }

  /**
   * Find word definition in dictionary.
   *
   * @param word word to search
   * @return definition of word in dictionary or empty optional if it is not there
   */
  private static Optional<String> findWordInDictionary(String word,
                                                       MyHashTable<String, String> dict) {
    return dict.get(word.toUpperCase());
  }


  private static long benchmark(MyHashTable<String, Integer> dict, int testNum) {
    String[] keys = getRandomKeys(testNum);
    long[] time = new long[testNum];

    for (int i = 0; i < testNum; i++) {
      dict.put(keys[i], i);
    }

    for (int i = 0; i < testNum; i++) {
      long start = System.nanoTime();
      dict.get(keys[i]);
      time[i] = System.nanoTime() - start;
    }

    return Arrays.stream(time).sum() / testNum;
  }

  private static String[] getRandomKeys(int amount) {
    String[] keys = new String[amount];
    for (int i = 0; i < amount; i++) {
      keys[i] = getRandomString(new Random().nextInt(10));
    }
    return keys;
  }

  private static String getRandomString(int length) {
    String alpha = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    StringBuilder value = new StringBuilder();
    for (int i = 0; i < length; i++) {
      value.append(alpha.charAt(new Random().nextInt(alpha.length())));
    }
    return value.toString();
  }
}
