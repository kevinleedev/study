package study.zookeeper.apply;

import study.zookeeper.apply.distribute.lock.DistributeLock;
import study.zookeeper.apply.distribute.lock.ZookeeperDistributeLock;
import study.zookeeper.apply.distribute.lock.ZookeeperDistributeReleaseLock;

import java.util.concurrent.CountDownLatch;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/30 20:09
 **/
public class Main implements Runnable {

    public static final int COUNT = 20;

    private OrderNoGenerator generator = new OrderNoGenerator();

    private static CountDownLatch cdl = new CountDownLatch(COUNT);

    private static Object mutex = new Object();

//    private DistributeLock lock = new ZookeeperDistributeLock();
    private DistributeLock lock = new ZookeeperDistributeReleaseLock("/disLock");

    protected void createOrderNoJVM() {
        synchronized (mutex) {
            String orderNo = generator.generateOrderNo();
            System.out.println(Thread.currentThread().getName() + "==>" + orderNo);
        }
    }

    public void run() {
//        createOrderNoJVM();
        createOrderNo();
    }

    protected void createOrderNo() {
        lock.lock();
        String orderNo = generator.generateOrderNo();
        System.out.println(Thread.currentThread().getName() + "==>" + orderNo);
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<COUNT; i++) {
            new Thread(new Main()).start();
            cdl.countDown();
        }
        cdl.await();
    }

}
