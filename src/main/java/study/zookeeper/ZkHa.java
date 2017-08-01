package study.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkHa {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZkHa.class);

    private static final String MASTER_IP = "192.168.1.1";
    private static final String STANDBY_IP = "192.168.1.2";

    public static void main(String[] args) throws Exception {
        new ZkHa().ha();
    }

    public void ha() throws Exception {
        ZooKeeper zk = ZkUtils.connectZk("127.0.0.1:2181");
        if(null == zk) return;

        if(null != zk.exists("/master", new ServerWatcher())) {
            // master node exists，current connection standby
            LOGGER.info("master is active now, please standby. The ip is {}", MASTER_IP);
        } else {
            // master node does not exists,create the node
            zk.create("/master", MASTER_IP.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            LOGGER.info("master node does not exists, create the node[{}]", MASTER_IP);
        }

        Thread.sleep(5000);

        ZkUtils.closeZk(zk);

    }

    class ServerWatcher implements Watcher {

        public void process(WatchedEvent event) {
            if(event.getType() == Event.EventType.NodeDeleted) {
                // master node was deleted, switch standby to active
                LOGGER.info("switch to active now. The ip is {}", STANDBY_IP);
            }
        }
    }

}
