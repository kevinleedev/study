package study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 *   1:27
 */
public class ZookeeperClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperClient.class);

    private static final String ZK_URL = "127.0.0.1:2181";

    public static void main(String[] args) {
        new ZookeeperClient().testZookeeper();
    }

    private void testZookeeper() {
        ZooKeeper zooKeeper =ZkUtils.connectZk(ZK_URL);

        try {
            Stat exists = zooKeeper.exists("/kevin", false);
            if(null == exists) {
                String result = zooKeeper.create("/kevin", "value of node[kevin]".getBytes(), OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                LOGGER.info("node kevin not exists, create result=>", result);
            } else {
                LOGGER.info("node kevin exists");
            }
            Stat stat = new Stat();
            byte[] data = zooKeeper.getData("/kevin", false, stat);
            String value = new String(data, "UTF-8");
            LOGGER.info("node kevin value=>{}", value);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ZkUtils.closeZk(zooKeeper);
    }

}
