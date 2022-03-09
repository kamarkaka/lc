package com.kamarkaka.indeed;

import java.util.*;

public class MergeSortedLists {
   public List<Integer> getTopK(List<Stream> streams, int k) {
      List<Integer> res = new ArrayList<>();
      if (streams == null || streams.size() == 0) return res;

      PriorityQueue<Num> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

      for (Stream stream : streams) {
         if (stream.move()) {
            minHeap.add(new Num(stream));
         }
      }

      while (!minHeap.isEmpty()) {
         Num currNum = minHeap.poll();
         int count = 1;

         int currVal = currNum.val;
         currNum.skipCurrentValue();
         if (currNum.stream.move()) minHeap.add(currNum);

         while (!minHeap.isEmpty() && currVal == minHeap.peek().val) {
            count++;
            Num num = minHeap.poll();

            num.skipCurrentValue();
            if (num.stream.move()) minHeap.add(num);
         }

         if (count >= k) {
            res.add(currVal);
         }
      }

      return res;
   }

   public static void run() {
      MergeSortedLists msl = new MergeSortedLists();
      List<Stream> streams = new ArrayList<>();
      streams.add(new Stream(Arrays.asList(1,2,3,4)));
      streams.add(new Stream(Arrays.asList(2,5,6)));
      streams.add(new Stream(Arrays.asList(2,5,7)));

      List<Integer> res = msl.getTopK(streams, 2);
      System.out.println(res);
   }
}

class Stream {
   Iterator<Integer> iterator;

   public Stream(List<Integer> list) {
      this.iterator = list.iterator();
   }

   public boolean move() {
      return iterator.hasNext();
   }

   public int getValue() {
      return iterator.next();
   }
}

class Num {
   Stream stream;
   int val;

   public Num(Stream stream) {
      this.stream = stream;
      this.val = stream.getValue();
   }

   public void skipCurrentValue() {
      while (stream.move()) {
         int nextVal = stream.getValue();
         if (nextVal == val) continue;

         val = nextVal;
         break;
      }
   }

}