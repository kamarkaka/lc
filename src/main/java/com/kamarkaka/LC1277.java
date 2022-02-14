package main.java.com.kamarkaka;

/***
 * 1277. Count Square Submatrices with All Ones
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Example 1:
 *   Input: matrix =
 *   [
 *     [0,1,1,1],
 *     [1,1,1,1],
 *     [0,1,1,1]
 *   ]
 *   Output: 15
 *   Explanation:
 *     There are 10 squares of side 1.
 *     There are 4 squares of side 2.
 *     There is  1 square of side 3.
 *     Total number of squares = 10 + 4 + 1 = 15.
 *
 * Example 2:
 *   Input: matrix =
 *   [
 *     [1,0,1],
 *     [1,1,0],
 *     [1,1,0]
 *   ]
 *   Output: 7
 *   Explanation:
 *     There are 6 squares of side 1.
 *     There is 1 square of side 2.
 *     Total number of squares = 6 + 1 = 7.
 *
 * Constraints:
 *   1 <= arr.length <= 300
 *   1 <= arr[0].length <= 300
 *   0 <= arr[i][j] <= 1
 */
public class LC1277 {
   public int countSquares(int[][] matrix) {
      int count = 0;
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[0].length; j++) {
            int len = 1;
            while (true) {
               boolean isSquare = true;
               for (int k1 = i; k1 < i + len; k1++) {
                  for (int k2 = j; k2 < j + len; k2++) {
                     if (matrix[k1][k2] == 0) {
                        isSquare = false;
                        break;
                     }
                  }
                  if (!isSquare) break;
               }

               if (isSquare) {
                  count++;
                  len++;

                  if (i + len > matrix.length) break;
                  if (j + len > matrix[0].length) break;
               } else {
                  break;
               }
            }
         }
      }
      return count++;
   }

   public int countSquares2(int[][] matrix) {
      int rows = matrix.length;
      int cols = matrix[0].length;

      int ans = 0;
      for (int i = 0; i < cols; i++)
         ans += matrix[0][i];
      for (int i = 1; i < rows; i++) {
         ans += matrix[i][0];
      }
      for (int i = 1; i < rows; i++) {
         for (int j = 1; j < cols; j++) {
            if (matrix[i][j] == 1) {
               matrix[i][j] = 1 + Math.min(
                  matrix[i - 1][j - 1],
                  Math.min(
                     matrix[i - 1][j],
                     matrix[i][j - 1]
                  )
               );
               ans += matrix[i][j];
            }
         }
      }

      return ans;
   }
}
