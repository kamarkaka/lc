package com.kamarkaka.datadog;

/***
 * 套了一层FileWriter的外皮，其实就是有一个空的固定size的byte array
 * 写一个method可以往里边从左到右append bytes
 * 再写一个method可以flush/清空byte array里面的bytes
 * 这个flush method可以直接被call，或者当append operation时候内部的byte array满了，
 * 就需要内部call flush之后再resume append operation。
 * follow up就是每次flush operation不会全部清空所有的existing bytes，而是左边的一部分，至于具体多少呢，他会以数字的形式换会告诉你，
 * 你需要handle这个情况，知道完成flush的使命。
 * implement a buffered writer: 这个writer存在一个buffer和可设置的最大buffer size，这里的buffer工作方式是
 * 1，读取的数据先存在buffer里面不写入硬盘
 * 2，当buffer里的数据量达到buffer size的时候，一次性清空所有buffer内的数据到硬盘中。如此往复。
 * 给你一些文件的接口函数：
 * write(const uint8_t* bytes, int nBytes)
 * flush()
 * 然后实现BufferedFile类，要求模拟一个memory storage
 * constructor： (File* f, int nMaxBufferedBytes)；nMaxBufferedBytes is memory size
 * write(const uint8_t* bytes, int nBytes)： 优先写入memory，memory FIFO写入disk
 * flush(): from mem to disk
 */
public class BufferedWriter {
    private final byte[] buffer;
    private int index;

    public BufferedWriter(int bufferSize) {
        this.buffer = new byte[bufferSize];
        this.index = 0;
    }

    public synchronized void write(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            this.buffer[this.index++] = bytes[i];

            if (this.index == this.buffer.length) {
                this.flush();
            }
        }
    }

    public synchronized void flush() {
        for (int i = 0; i < this.index; i++) {
            System.out.println(this.buffer[i]);
        }
        this.index = 0;
    }

    public boolean test() {
        if (buffer[0] == 0 && buffer[1] == 0 && buffer[2] == 0 && buffer[3] == 0 && buffer[4] == 1 && buffer[5] == 1 && buffer[6] == 1 && buffer[7] == 1) return true;
        else if (buffer[0] == 1 && buffer[1] == 1 && buffer[2] == 1 && buffer[3] == 1 && buffer[4] == 0 && buffer[5] == 0 && buffer[6] == 0 && buffer[7] == 0) return true;
        else return false;
    }

    public void print() {
        for (int i = 0; i < this.buffer.length; i++) {
            System.out.println(this.buffer[i]);
        }
        System.out.println("index: " + this.index);

    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            System.out.println("try " + i++);
            BufferedWriter bw = new BufferedWriter(16);
            Thread t1 = new Thread(() -> {
                bw.write(new byte[] { 0,0,0,0 });
            });
            Thread t2 = new Thread(() -> {
                bw.write(new byte[] { 1,1,1,1 });
            });
            t1.start();
            t2.start();

            t2.join();
            t1.join();

            if (!bw.test()) {
                bw.print();
                break;
            }
        }
    }
}
