package study.concurrency.dp.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * <p>desc: 使用内部类实现的线程安全的单例模式</p>
 * author: lilin
 * created: 2017/8/1 21:21
 **/
public class InnerClassSingleton {

    private static class Inner {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance() {
        return Inner.instance;
    }

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(10);
        for(int i=0; i<10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(InnerClassSingleton.getInstance());
                }
            }).start();
            cdl.countDown();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
