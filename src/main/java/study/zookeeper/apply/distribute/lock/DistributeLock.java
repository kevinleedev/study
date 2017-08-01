package study.zookeeper.apply.distribute.lock;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/30 21:00
 **/
public interface DistributeLock {

    void lock();

    void unlock();

}
