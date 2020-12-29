package com.mxw.hbase.service.impl;

import com.mxw.hbase.service.HBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.shaded.com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Hbase业务逻辑处理接口实现类
 *
 * @author Ma_wei
 * @since 2020/11/12 17:15
 */
@Slf4j
@Service
public class HBaseServiceImpl implements HBaseService {

    /**
     * 内部已实现线程安全的连接池
     */
    @Autowired
    private Connection hbaseConnection;

    private static final String NAME_SPACE = "test";

    private static final String TABLE_NAME = "student";

    public String test2() throws IOException {
        log.info("HBaseServiceImpl.test1");

        //
        // 配置 & 连接
        //
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "47.95.228.76");
        configuration.set("zookeeper.znode.parent", "/hbase");
        configuration.set("hbase.zookeeper.property.clientPort","2185");

        Connection connection = ConnectionFactory.createConnection(configuration);

        try (Table table = connection.getTable(TableName.valueOf("test:student"))) {
            //
            // HBase 数据表操作
            //
            Get get = new Get("1".getBytes());
            Result result = table.get(get);
            log.info(result.toString());
        }

        /*hbaseTemplate.get(NAME_SPACE + ":" + TABLE_NAME, "1", new RowMapper<Object>() {
            @Override
            public Object mapRow(Result result, int i) throws Exception {
                log.info(result.toString());
                return null;
            }
        });*/
       /* hbaseTemplate.put(TABLE_NAME,"r2","cf2","school","yao".getBytes());
        Object obj = hbaseTemplate.get(TABLE_NAME, "r1", new RowMapper<Object>() {

            @Override
            public Object mapRow(Result result, int i) throws Exception {
                log.info(result.toString());
                return result;
            }
        });
        log.info(obj.toString());*/
        return null;
    }

    @Override
    public String test1() throws IOException {
        try (Table table = hbaseConnection.getTable(TableName.valueOf(NAME_SPACE + ":" + TABLE_NAME))) {//获取表连接
            Result result = table.get(new Get("1".getBytes()));
            if (result == null) return null;

            // 列名为starttime，最后一条就是该航班最新的航迹
            Cell latestCell = Iterables.getLast(result.listCells());
            return new String(CellUtil.cloneValue(latestCell));
        }
    }
}
