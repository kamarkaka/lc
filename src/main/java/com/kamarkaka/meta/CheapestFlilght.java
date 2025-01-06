package com.kamarkaka.meta;

/***
 * There are 2 arrays which denote departing and returning flights with the respective indexes being time and the values
 * of the array being the cost it takes for the flight. Return the minimum cost for a round trip provided the return
 * flight can only be taken at a time post departing flight time (i.e if departing at time i, one can catch a returning
 * flight only from time (i+1) onwards). For eg departing = [1,2,3,4] and returning = [4,3,2,1], the minimum cost for
 * round trip will be 2 i.e departing[0] + returning[3]. Solve this is O(n) time
 */
public class CheapestFlilght {
   public int getMin(int[] departing, int[] returning) {
      int[] suffixMin = new int[returning.length];
      int min = Integer.MAX_VALUE;
      for (int i = returning.length - 1; i >= 1; --i) {
         if (returning[i] < min) {
            min = returning[i];
         }
         suffixMin[i] = min;
      }

      int result = Integer.MAX_VALUE;
      for (int i = 0; i < departing.length - 1; ++i) {
         result = Math.min(result, departing[i] + suffixMin[i + 1]);
      }
      return result;
   }

   public static void main(String[] args) {
      CheapestFlilght solution = new CheapestFlilght();
      System.out.println(solution.getMin(new int[]{1,2,3,4}, new int[]{4,3,2,1}));
      System.out.println(solution.getMin(new int[]{3,2,1,4}, new int[]{4,3,1,2}));
   }
}
