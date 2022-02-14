package main.java.com.kamarkaka;

public class LC1395 {
   public int numTeams(int[] rating) {
      int res = 0;
      for (int mid = 1; mid < rating.length - 1; mid++) {
         int leftSmaller = 0, leftGreater = 0;
         int rightGreater = 0, rightSmaller = 0;
         for (int left = 0; left < mid; left++) {
            if (rating[left] < rating[mid])
               leftSmaller++;
            else
               leftGreater++;
         }

         for (int right = mid + 1; right < rating.length; right++) {
            if (rating[mid] < rating[right])
               rightGreater++;
            else
               rightSmaller++;
         }

         res += leftSmaller * rightGreater;
         res += leftGreater * rightSmaller;
      }
      return res;
   }

   public int numTeams2(int[] rating) {
      int[] idp = new int[rating.length];
      int[] ddp = new int[rating.length];
      int res = 0;
      for (int i = 0; i < rating.length; i++) {
         for (int j = 0; j < i; j++) {
            if (rating[i] > rating[j]) {
               idp[i]++;
               res += idp[j];
            } else {
               ddp[i]++;
               res += ddp[j];
            }
         }
      }
      return res;
   }

   public static void run() {
      LC1395 solution = new LC1395();
      System.out.println(solution.numTeams(new int[] {2,5,3,4,1}));
   }
}
