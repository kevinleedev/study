package study.zookeeper.apply.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.exception.ZkException;

import java.util.concurrent.CountDownLatch;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/30 21:05
 **/
public class ZookeeperDistributeLock extends AbstractDistributeLock {

    private CountDownLatch cdl = null;

    protected boolean tryLock() {
        try {
            client.createEphemeral(path);
            return true;
        } catch (ZkException e) {
            return false;
        }
    }

    protected void wait4Lock() {
        //创建监听器
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                if(null != cdl) {
                    cdl.countDown();
                }
            }

            public void handleDataDeleted(String s) throws Exception {

            }
        };

        client.subscribeDataChanges(path, listener);

        if(client.exists(path)) {
            // 等待
            cdl = new CountDownLatch(1);
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //路径不存在，删除监听器
        client.unsubscribeDataChanges(path, listener);
    }

}
