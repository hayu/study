/********************************************************************************
 * Copyright (c) 2015-2017 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package hello.learn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Albert H. Yu 212365823
 * @version 1.0 May 16, 2018
 * @since 1.0
 */
public class LongestPalindromeSubstring {

  @Test
  public void isPalindrome() {
    String[] inputs = {"", "a", "ab", "cc", "madam", "dad", "geeksforgeeks", "abccba"};
    boolean[] expected = {true, true, false, true, true, true, false, true};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], isPalindrome(inputs[i]));
    }
  }
  private boolean isPalindrome(String input) {
    if (input.length() == 0) {
      return true;
    }
    int begin = 0;
    int end = input.length() - 1; // 4, 5
    int mid = (begin + end) / 2 + 1; // 3, 3
    for (int i = 0; i < mid; i++) {
      if (input.charAt(begin) != input.charAt(end)) {
        break;
      }
      begin++;
      end--;
    }

    return begin == mid;
  }

  /**
   * O(n^3)
   */
  @Test
  public void bruteForce() {
    String[] inputs = {"", "a", "def", "aabbcc", "madam", "abracadabra", "bananas", "forgeeksskeegfor"};
    String[] expected = {"", "a", "d", "aa", "madam", "aca", "anana", "geeksskeeg"};
    for (int i = 0, size = inputs.length; i < size; i++) {
      assertEquals(expected[i], bruteForce(inputs[i]));
    }
  }
  private String bruteForce(String input) {
    int len = input.length();
    for (int i = 0; i < len; i++) {
      for (int j = 0; j <= i; j++) {
        // for a string of length 5, this will print (beginIndex, endIndexExclusive)
        // (0,5), (0,4), (1,5), (0,3), (1,4), (2,5), (0,2), (1,3), (2,4), (3,5), (0,1), (1,2), (2,3), (3,4), (4,5)
        // for "geeks", this would print: geeks, geek, eeks, gee, eek, eks, ge, ee, ek, ks, g, e, e, k, s
        //System.out.println("(" + j + ", " + (len - (i - j)) + ")");
        String sub = input.substring(j, (len - i + j)); // pick longer substring first, as an optimization
        if (isPalindrome(sub)) {
          return sub;
        }
      }
    }
    return "";
  }

}
