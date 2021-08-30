package com.yy.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName HBaseUtils
 * @Author yy
 * @Description
 * @Date 2021/7/26 14:18
 * @Version 1.0
 **/
@Slf4j
public class HBaseUtils {
    private static final String KEY_HDFS_KERBEROS_FILE = "KEYTAB";
    private static final String KEY_HDFS_KERBEROS_PRINCIPAL = "PRINCIPAL";
    private static Configuration conf;
    private static UserGroupInformation ugi;
    private static boolean isKerberos = true;
    private static Connection connection;
    private static Admin admin;
    private static String ZK_HOST = "master02,slave01,master01";
    private static String POOL_TYPE = "RoundRobin";
    private static String POOL_THEARDS = "4";

    /***
     *  Kerberos认证
     * @param conf
     * @param principal
     * @param keytab
     * @return
     */
    private static UserGroupInformation login(Configuration conf, String principal, String keytab) {
        try {
            if (isKerberos) {
                log.debug("principal: {}, keytab:{}", principal, keytab);
                conf.set(KEY_HDFS_KERBEROS_PRINCIPAL, principal);
                conf.set(KEY_HDFS_KERBEROS_FILE, keytab);
                conf.set("hadoop.security.authentication", "kerberos");
                UserGroupInformation.setConfiguration(conf);
                UserGroupInformation tmpugi = UserGroupInformation.loginUserFromKeytabAndReturnUGI(conf.get(KEY_HDFS_KERBEROS_PRINCIPAL), conf.get(KEY_HDFS_KERBEROS_FILE));
                UserGroupInformation.setLoginUser(tmpugi);
                return tmpugi;
            } else {
                return UserGroupInformation.getCurrentUser();
            }
        } catch (IOException e) {
            log.error("kerberos认证失败, principal:{}, keytab:{}", principal, keytab, e);
        }
        return null;
    }

    /***
     * hbase配置初始化
     * @return
     */
    public synchronized static Admin initHBaseConn() {
//        conf.addResource(new Path("D:\\IdeaProject\\gmail\\gmail-logger\\src\\main\\resources\\core-site.xml"));
//        conf.addResource(new Path("D:\\IdeaProject\\gmail\\gmail-logger\\src\\main\\resources\\hdfs-site.xml"));
//        conf.addResource(new Path("D:\\IdeaProject\\gmail\\gmail-logger\\src\\main\\resources\\hbase-site.xml"));
//        System.setProperty("java.security.krb5.conf", "D:\\IdeaProject\\gmail\\krb5.conf");
//        isKerberos = "kerberos".equalsIgnoreCase(conf.get("hadoop.security.authentication"));
//        ugi = login(conf, "brd", "D:\\IdeaProject\\gmail\\brd.keytab");

        try {
            conf = HBaseConfiguration.create();
            conf.set("hbase.client.ipc.pool.type",POOL_TYPE);
            conf.set("hbase.client.ipc.pool.size", POOL_THEARDS);
            conf.set("hbase.zookeeper.quorum", ZK_HOST);
            conf.set("hbase.zookeeper.property.clientPort", "2181");
            conf.set("zookeeper.znode.parent", "/hbase");
            connection = ConnectionFactory.createConnection(conf, Executors.newScheduledThreadPool(2));
            admin = connection.getAdmin();
            return admin;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void createTable(String tableName, String... columnFamily) {

        try {
            conf = new Configuration();
            connection = ConnectionFactory.createConnection(conf);
            if (admin.tableExists(TableName.valueOf(tableName))) {
                return;
            }
            //创建表属性对象,表名需要转字节
            HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(tableName));
            //创建多个列族
            for (String cf : columnFamily) {
                descriptor.addFamily(new HColumnDescriptor(cf));
            }
            //根据对表的配置，创建表
            try {
                admin.createTable(descriptor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("表" + tableName + "创建成功！");


    }

    public static TableName[] showTables() {
        if (admin == null) {
            log.error("hbase ");
        }
        log.info("query starting......");
        TableName[] lst = null;
        try {
            lst = admin.listTableNames();
            for (TableName tab : lst) {
                log.info("table name is: " + tab);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }


    public static void queryDataByXdrid(String name) {

        try {
            final Table table = connection.getTable(TableName.valueOf(name));
            final Scan scan = new Scan();
            final ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                final Cell[] cells = result.rawCells();
                for (Cell cell : cells) {
                    log.info("值:" + Bytes.toString(CellUtil.cloneValue(cell)));
//                    final FileOutputStream out = new FileOutputStream(new File("./" + name));
//                    out.write(CellUtil.cloneValue(cell));
                }
            }
        } catch (Exception e) {

        }


    }

    public static void queryDataByRowkey(String name) {

        try {
            final Table table = connection.getTable(TableName.valueOf(name));
            final Get get = new Get("1001".getBytes());
            final Result result = table.get(get);
            final Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                log.info("列族:" + CellUtil.cloneFamily(cell));
                log.info("值:" + Bytes.toString(CellUtil.cloneValue(cell)));
                log.info("ROW:" + Bytes.toString(CellUtil.copyRow(cell)));
                final FileOutputStream out = new FileOutputStream(new File("./" + name));
                out.write(CellUtil.cloneValue(cell));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void sumOneNumber(int a, int b) {
        for (int i = 0; i < 100; i++) {
            System.out.println(a + b);
//            System.out.println(Thread.currentThread().getId());
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");
            System.out.println(" ");
        }
    }

    public static void putItem() throws IOException {
        final Table tab = connection.getTable(TableName.valueOf("a"));
        final Put put = new Put(Bytes.toBytes("10006"));
        put.addColumn("c".getBytes(),"sex".getBytes(),"[B@3e5d9eab".getBytes());
        tab.put(put);
        tab.close();
    }
    @SneakyThrows
    public static void main(String[] args) {
//        //1.加载驱动程序
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.获得数据库的连接
//        final java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.5.129:3306/sharepro", "root", "broadtech");
//        //3.通过数据库的连接操作数据库，实现增删改查
//        final HashMap<String, java.sql.Connection> map = new HashMap<>();
//        map.put("1",conn);
//            Statement stmt = map.get("1").createStatement();
//
//        ResultSet rs = stmt.executeQuery("select id from task");//选择import java.sql.ResultSet;
//            while(rs.next()){//如果对象中有数据，就会循环打印出来
//                    System.out.println(rs.getString("id"));
//                }
//            map.get("1").close();
//        if (map.get("1")==null) {
//            System.out.println("null--------------------");
//            return;
//        }
//        Statement stmt2 = map.get("1").createStatement();
//
//        ResultSet rs2 = stmt.executeQuery("select id from task");//选择import java.sql.ResultSet;
//        while(rs.next()){//如果对象中有数据，就会循环打印出来
//            System.out.println(rs.getString("id"));
//        }
////        final Number add =  HBaseUtils.<Number>add(1, 1.2);
////        final Serializable s = add(1, "s");
////        final Object  s2 = HBaseUtils.<Serializable>add(1, "s");
////        System.out.println(add);
////        System.out.println(s);
////        System.out.println(s2);

        System.out.println( StringUtils.reverse("298fc065612834dbbca34fa061283502".toUpperCase()));


    }

    public  static  <T> T add(T x,T y){
        return y;
    }
}
