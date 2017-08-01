package study.concurrency.producerconsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/31 22:19
 **/
public class WaitNotify<T> {

    private LinkedList<T> queue = new LinkedList<T>();
    private static final int SIZE = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while(queue.size() == SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(t);
        notifyAll();
        ++count;
    }

    public synchronized T get() {

        while (0 == queue.size()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t = queue.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        // 消费者线程
        final WaitNotify<String> consumer = new WaitNotify<String>();
        for(int i=0; i<10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("消费者消费数据："+ consumer.get());
                }
            }).start();
        }

        while(true) {
            InputStream in = System.in;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            try {
                String input = br.readLine();
                consumer.put(input);
                System.out.println("生产者生产数据：" + input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
