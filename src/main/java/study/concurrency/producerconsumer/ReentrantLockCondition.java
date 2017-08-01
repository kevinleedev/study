package study.concurrency.producerconsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/31 22:33
 **/
public class ReentrantLockCondition<T> {

    private LinkedList<T> queue = new LinkedList<T>();
    private int SIZE = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public ReentrantLockCondition() {

    }

    public ReentrantLockCondition(Integer initialSize) {
        if(null != initialSize) {
            SIZE = initialSize;
        }
    }

    public void put(T t) {
        try {
            lock.lock();
            while(SIZE == count) { // 满了，生产者等待
                producer.await();
            }
            queue.add(t);
            // 通知消费者可以消费了
            consumer.signalAll();
            // 个数加1
            ++count;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(0 == count) { //没有数据可以消费，消费者等待
                consumer.await();
            }

            t = queue.removeFirst();
            //通知生产者可以生产了
            producer.signalAll();
            count--;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        // 消费者线程
        final ReentrantLockCondition<String> consumer = new ReentrantLockCondition<String>();
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
