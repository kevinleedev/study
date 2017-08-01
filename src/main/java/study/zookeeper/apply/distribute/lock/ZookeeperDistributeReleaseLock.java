package study.zookeeper.apply.distribute.lock;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>desc: </p>
 * author: lilin
 * created: 2017/7/30 22:10
 **/
public class ZookeeperDistributeReleaseLock extends AbstractDistributeLock {

    private CountDownLatch cdl = null;

    private String previousPath; // 前一个节点
    private String currentPath; // 当前节点

    public ZookeeperDistributeReleaseLock(String path) {
        this.path = path;
        if(!client.exists(path)) {
            client.createPersistent(path, "lock");
        }
    }

    protected boolean tryLock() {
        if(null == currentPath || 0 == currentPath.length()) { // 当前节点还未创建
            currentPath = client.createEphemeralSequential(path + "/", "lock");
        }

        // 当前节点已有，则找到path的所有子节点，按从小到大排序
        // 子节点中当前节点最小，则当前节点获取锁
        // 当前节点不是最小，找到当前节点的前一个节点，监听该节点
        List<String> children = client.getChildren(path);
        Collections.sort(children);

        String smallestNode = path + "/" + children.get(0);
        if(currentPath.equals(smallestNode)) {
            return true;
        } else {
            int currentNodePathIndex = path.indexOf("/", 2);
            if(currentNodePathIndex > 0) {
                int currentPathIndex = Collections.binarySearch(children, currentPath.substring(currentNodePathIndex));
                previousPath = children.get(currentPathIndex - 1);
            } else {
                previousPath = "/" + children.get(0);
            }
        }
        return false;
    }

    protected void wait4Lock() {
        //创建监听器
        IZkDataListener listener = new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {

            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println(Thread.currentThread().getName() + "监听到handleDataChange");
                if(null != cdl) {
                    cdl.countDown();
                }
            }
        };

        client.subscribeDataChanges(previousPath, listener);

        if(client.exists(previousPath)) {
            // 等待
            cdl = new CountDownLatch(1);
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //路径不存在，删除监听器
        client.unsubscribeDataChanges(previousPath, listener);
    }

}
