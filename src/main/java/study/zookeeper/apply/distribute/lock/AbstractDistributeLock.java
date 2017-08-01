package study.zookeeper.apply.distribute.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/30 21:01
 **/
public abstract class AbstractDistributeLock implements DistributeLock {

    private static final String ZK_PATH = "localhost:2181";

    protected ZkClient client = new ZkClient(ZK_PATH);

    protected String path;

    public void lock() {
        if(tryLock()) {
            System.out.println(Thread.currentThread().getName() + "==>get lock.");
        } else {
            wait4Lock();
            lock();
        }
    }

    public void unlock() {
        client.close();
    }

    protected abstract boolean tryLock();

    protected abstract void wait4Lock();

}
