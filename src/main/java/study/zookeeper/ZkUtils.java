package study.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkUtils.class);


    public static ZooKeeper connectZk(String url) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(url, 3000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    Event.KeeperState state = watchedEvent.getState();
                    if(state == Event.KeeperState.SyncConnected) {
                        countDownLatch.countDown();
                        LOGGER.info("connected and count down.");
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("zookeeper connected.");
        return zk;
    }

    public static void closeZk(ZooKeeper zk) {
        if(null != zk) {
            try {
                zk.close();
                LOGGER.info("zookeeper closed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
