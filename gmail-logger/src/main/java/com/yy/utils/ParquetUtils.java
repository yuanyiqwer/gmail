package com.yy.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.GroupWriteSupport;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.parquet.example.Paper.schema;

/**
 * @ClassName ParquetUtils
 * @Author yy
 * @Description
 * @Date 2021/10/8 10:20
 * @Version 1.0
 **/
public class ParquetUtils {
    static MessageType schema;
    static {
         schema = MessageTypeParser.parseMessageType("message Pair {\n" +
                        " required binary city (UTF8);\n" +
                        " required binary ip (UTF8);\n" +
                        " repeated group time {\n"
                       +" required int32 ttl;\n"
                       + " required binary ttl2;\n" +
                        "}\n" +
                        "}");
    }

    public static void main(String[] args) throws IOException {
         SimpleGroupFactory factory = new SimpleGroupFactory(schema);
         Path path = new Path("C:\\Users\\Machenike\\Desktop\\新建文件夹\\test11");
         Configuration entries = new Configuration();
//        entries.addResource(new  FileInputStream(new File("C:\\Users\\Machenike\\Desktop\\hbase\\core-site.xml")));
         GroupWriteSupport writeSupport = new GroupWriteSupport();
        writeSupport.setSchema(schema,entries);
         ParquetWriter<Group> writer = new ParquetWriter<>(path, entries, writeSupport);
         Group append = factory.newGroup()
                .append("city", "bj")
                .append("ip", "123");
        writer.write(append);
        writer.close();
    }
}
