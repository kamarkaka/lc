package main.java.com.kamarkaka.twosigma;

import java.util.*;

public class IPO {

   /*
    * Complete the 'getResults' function below.
    *
    * The function is expected to return an INTEGER_ARRAY.
    * The function accepts following parameters:
    *  1. 2D_INTEGER_ARRAY bids
    *  2. INTEGER totalShares
    */

   private static final int ID = 0;
   private static final int SHARES = 1;
   private static final int PRICE = 2;
   private static final int TIMESTAMP = 3;

   public List<Integer> getResults(List<List<Integer>> bids, int totalShares) {
      bids.sort((bid1, bid2) -> {
         if (!Objects.equals(bid1.get(PRICE), bid2.get(PRICE))) return bid2.get(PRICE) - bid1.get(PRICE);
         if (!Objects.equals(bid1.get(TIMESTAMP), bid2.get(TIMESTAMP))) return bid1.get(TIMESTAMP) - bid2.get(TIMESTAMP);
         return bid1.get(ID) - bid2.get(ID);
      });

      Set<Integer> bidders = new HashSet<>();
      int index = 0;
      while (totalShares > 0 && index < bids.size()) {
         if (bids.get(index).get(SHARES) == 0) {
            index++;
            continue;
         }

         int bidPrice = bids.get(index).get(PRICE);
         int i = index;
         if (i == bids.size() - 1 || bids.get(i+1).get(PRICE) != bidPrice) {
            int shares = Math.min(totalShares, bids.get(i).get(SHARES));
            bids.get(i).set(SHARES, bids.get(i).get(SHARES) - shares);
            totalShares -= shares;
            bidders.add(bids.get(i).get(ID));

            if (totalShares == 0) break;
         } else {
            while (i < bids.size() && bids.get(i).get(PRICE) == bidPrice) {
               if (bids.get(i).get(SHARES) > 0) {
                  bids.get(i).set(SHARES, bids.get(i).get(SHARES) - 1);
                  totalShares--;
                  bidders.add(bids.get(i).get(ID));

                  if (totalShares == 0) break;
               }
               i++;
            }
         }
      }

      List<Integer> res = new ArrayList<>();
      for (List<Integer> bid : bids) {
         if (!bidders.contains(bid.get(ID))) {
            res.add(bid.get(ID));
         }
      }
      Collections.sort(res);
      return res;
   }

}
