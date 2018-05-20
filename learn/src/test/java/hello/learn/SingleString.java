package hello.learn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * @author Albert H. Yu 212365823
 * @version 1.0 May 16, 2018
 * @since 1.0
 */
public class SingleString {

  static final int NUM_EXTENDED_ASCII = 256;
  static final char DEFAULT_CHAR = '\u0000';

  /**
   * Given a string, find its first duplicate characters.
   *
   * Use HashMap for efficiency.
   */
  @Test
  public void firstDupe() {
    String[] inputs = {"", "ABCA", "BCBCA", "ABC"};
    String[] expected = {"", "A", "B", ""};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], firstDupe(inputs[i]));
    }
  }
  private String firstDupe(String input) {
    Map<String, Boolean> map = new HashMap<>();
    for (String c : input.split("")) {
      if (Boolean.TRUE.equals(map.get(c))) {
        return c;
      }
      map.put(c, Boolean.TRUE);
    }
    return "";
  }

  /**
   * Given a string, find its first unique character.
   *
   * Use an int array where index is the char of the character in the string. First pass to accumulate the counts of
   * any given char. Then 2nd pass to pick out the first char whose count value is one.
   */
  @Test
  public void firstNonDupe() {
    String[] inputs = {"", "geeksforgeeks", "geeksforgeek", "geeksforgeeksfor", "geeksrofgeeks"};
    String[] expected = {"", "f", "s", "", "r"};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], firstNonDupe(inputs[i]));
    }
  }
  private String firstNonDupe(String input) {
    int[] counts = new int[NUM_EXTENDED_ASCII];
    for (int i = 0, len = input.length(); i < len; i++) {
      counts[input.charAt(i)]++;
    }
    for (int i = 0, len = input.length(); i < len; i++) {
      if (counts[input.charAt(i)] == 1) {
        return String.valueOf(input.charAt(i));
      }
    }

    return "";
  }

  /**
   * Given a string, check if its characters are all unique.
   *
   * The special case of a string longer that the 256 possible ASCII must have duplicate.
   * Use an int array to keep count of each char as one walks the string. Short-circuit to return <code>false</code> if
   * any count is greater than one.
   */
  @Test
  public void isAllCharsUnique() {
    String[] inputs = {"", "geeksforgeeks", "abcdefg", "abcdefgab", null};
    StringBuilder base = new StringBuilder("abcdefghijklmopqrstuvwxyz0123456789");
    for (int i = 0; i < 3; i++) {
      base.append(base.toString());
    }
    inputs[inputs.length - 1] = base.toString();

    boolean[] expected = {true, false, true, false, true};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], isAllCharsUnique(inputs[i]));
    }
  }
  private boolean isAllCharsUnique(String input) {
    if (input.length() > NUM_EXTENDED_ASCII) {
      return true;
    }

    int[] count = new int[NUM_EXTENDED_ASCII];
    for (int i = 0, len = input.length(); i < len; i++) {
      char idx = input.charAt(i);
      count[idx]++;
      if (count[idx] > 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * Given a string, find the first longest repeat sequence.
   *
   * Walk a test string on paper to figure out what and how many variables are needed to keep track of
   * <ul>
   *   <li>Current character</li>
   *   <li>Previous character</li>
   *   <li>Max repeat character</li>
   *   <li>Max repeat length</li>
   *   <li>A running counter of how many repeated current character one has seen so far. This counter needs to be
   *   reset back to one whenever current and previous characters differ.</li>
   * </ul>
   */
  @Test
  public void firstLongestRepeatSequence() {
    String[] inputs = {"", "ABCDEF", "AABCDDBBBEEAA", "AABBCCDD", "AABBCCDDDEEEEE"};
    String[] expected = {null, null, "B3", "A2", "E5"};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], firstLongestRepeatSequence(inputs[i]));
    }
  }
  private String firstLongestRepeatSequence(String input) {
    char prevChar = DEFAULT_CHAR;
    char currChar;
    char maxChar = DEFAULT_CHAR;
    int maxLength = 0;
    int count = 0;

    for (int i = 0, len = input.length(); i < len; i++) {
      currChar = input.charAt(i);
      if (currChar == prevChar) {
        count++;
      } else {
        // need to reset counter
        count = 1;
      }
      if (count > maxLength) {
        maxChar = currChar;
        maxLength = count;
      }

      prevChar = input.charAt(i);
    }

    if (maxLength > 1) {
      return String.valueOf(maxChar) + String.valueOf(maxLength);
    }

    return null;
  }

  /**
   * Given a string, find all duplicate characters and return them as a string while preserving their orders in
   * the original string.
   *
   * Use LinkedHashMap which preserves insertion order.
   */
  @Test
  public void findAllDuplicateChars() {
    String[] inputs = {"", "great", "geeksforgeeks", "geeksforgeek", "geeksforgeeksfor", "geeksrofgeeks"};
    String[] expected = {"", "", "geks", "gek", "geksfor", "geks"};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], findAllDuplicateChars(inputs[i]));
    }
  }
  private String findAllDuplicateChars(String input) {
    Map<String, Integer> map = new LinkedHashMap<>();
    for (String c : input.split("")) {
      if (map.get(c) == null) {
        map.put(c, 1);
      } else {
        map.put(c, map.get(c).intValue() + 1);
      }
    }
    StringBuilder rslt = new StringBuilder();
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      if (e.getValue().intValue() > 1) {
        rslt.append(e.getKey());
      }
    }
    return rslt.toString();
  }

  /**
   * Return the count of a given charcter in a string without using any loop.
   *
   * Replace the given character with a blank in the string.
   */
  @Test
  public void countGivenCharacterInStringWithoutUsingLoop() {
    String[] inputs = {"", "great", "geeksforgeeks", "solid", "GREAT"};
    String given = "e";
    int[] expected = {0, 1, 4, 0, 0};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], countGivenCharacterInStringWithoutUsingLoop(inputs[i], given));
    }
  }
  private int countGivenCharacterInStringWithoutUsingLoop(String input, String givenChar) {
    return input.length() - input.replace(givenChar, "").length();
  }

  /**
   * Return the count of a given charcter in a string without using any loop.
   *
   * Replace the given character with a blank in the string.
   */
  @Test
  public void reverse() {
    String[] inputs = {"", "great", "geeksforgeeks", "solid", "GREAT"};
    String[] expected = {"", "taerg", "skeegrofskeeg", "dilos", "TAERG"};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], reverseUsingStack(inputs[i]));
      assertEquals(expected[i], reverseUsingRecursion(inputs[i]));
    }
  }
  private String reverseUsingStack(String input) {
    Stack<String> stack = new Stack<>();
    for (String c : input.split("")) {
      stack.push(c);
    }
    StringBuilder rslt = new StringBuilder();
    while (!stack.isEmpty()) {
      rslt.append(stack.pop());
    }
    return rslt.toString();
  }
  private String reverseUsingRecursion(String input) {
    // base case
    if (input.length() <= 1) {
      return input;
    }

    return reverseUsingRecursion(input.substring((1))) + // subproblem
        input.charAt(0);
  }

  public void findAllPermutations() {

  }

}
