package com.kamarkaka.bloomberg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomQueue<T> {
   private final Queue<T> queue;
   private final int capacity;
   private final ReentrantLock lock;
   private final Condition notEmpty;
   private final Condition notFull;

   public CustomQueue(int capacity) {
      this.queue = new LinkedList<>();
      this.capacity = capacity;
      this.lock = new ReentrantLock();
      this.notEmpty = this.lock.newCondition();
      this.notFull = this.lock.newCondition();
   }

   public boolean isEmpty() {
      return this.queue.isEmpty();
   }

   public T poll() {
      lock.lock();
      try {
         while (queue.isEmpty()) {
            notEmpty.await();
         }
         T item = queue.poll();
         notFull.signal();
         return item;
      } catch (InterruptedException e) {
         return null;
      } finally {
         lock.unlock();
      }
   }

   public void offer(T val) {
      lock.lock();
      try {
         while (queue.size() == capacity) {
            notFull.await();
         }
         queue.offer(val);
         notEmpty.signal();
      } catch (InterruptedException e) {
      } finally {
         lock.unlock();
      }
   }

   public static void run() {
      CustomQueue<Integer> queue = new CustomQueue<>(3);
      Thread thread1 = new Thread(() -> queue.offer(1));
      Thread thread2 = new Thread(() -> queue.offer(2));
      Thread thread3 = new Thread(() -> queue.offer(3));
      Thread thread4 = new Thread(() -> queue.offer(4));
      Thread thread5 = new Thread(() -> queue.poll());
      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
      thread5.start();
   }
}
