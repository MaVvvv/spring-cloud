package com.mawei.server;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-09 09:42
 */
public class ZookeeperClientDemo implements Watcher {

    private static final Logger LOG = LoggerFactory.getLogger(ZookeeperClientDemo.class);

    /** 并发计数器*/
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) {
        LOG.debug("启动Zookeeper连接clientDemo...");
        try {
            zooKeeper = new ZooKeeper("47.95.228.76:2181",50000,new ZookeeperClientDemo());
            countDownLatch.await();

            List<String> zNodes = zooKeeper.getChildren("/",true);
            zNodes.forEach(LOG::warn);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException | IOException | KeeperException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.debug("进入process方法...");
        LOG.info(watchedEvent.toString());
        if (watchedEvent.getType() == Event.EventType.None) {
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
        } else {
            try {
                List<String> zNodes = zooKeeper.getChildren("/",true);
                LOG.info("接收到新的改动根节点下children为：");
                zNodes.forEach(LOG::warn);
            } catch (Exception e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
