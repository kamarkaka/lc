package com.kamarkaka.leetcode;

/***
 * 1115. Print FooBar Alternately
 * Suppose you are given the following code:
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads:
 *   thread A will call foo(), while
 *   thread B will call bar().
 * Modify the given program to output "foobar" n times.
 * Example 1:
 *   Input: n = 1
 *   Output: "foobar"
 *   Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls
 *   bar(). "foobar" is being output 1 time.
 * Example 2:
 *   Input: n = 2
 *   Output: "foobarfoobar"
 *   Explanation: "foobar" is being output 2 times.
 * Constraints:
 *   1 <= n <= 1000
 */
public class LC1115 {
    private int n;
    private boolean print;

    public LC1115(int n) {
        this.n = n;
        this.print = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (!print) {
                    wait();
                }

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                print = false;
                notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this) {
                while (print) {
                    wait();
                }

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                print = true;
                notifyAll();
            }
        }
    }
}
