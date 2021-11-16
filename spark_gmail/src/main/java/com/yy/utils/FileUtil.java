package com.yy.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @ClassName FileUtil @Author yy @Description @Date 2021/11/8 14:18 @Version 1.0 */

@Slf4j
public class FileUtil {





    public static void main(String[] args) {

        System.out.println(StringUtils.reverse("0003B816000096920000000000000000").toLowerCase());
        System.out.println(Instant.now().toEpochMilli());

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        final String ts = sdf.format(new Date(Long.parseLong("1636511778748")));
        System.out.println(ts);
    }

    static Boolean createFile(String path) throws IOException {
        final File file = new File(path);
        return file.createNewFile();
    }

    public static RandomAccessFile createFile(String path, String permissions) {

        return createFile(path, permissions, 1024 * 1024 * 5);
    }

    public static RandomAccessFile createFile(String path, String permissions, int length) {

        try {
            RandomAccessFile file = new RandomAccessFile(new File(path), permissions);
            file.setLength(length);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("file=>\t{},创建失败", path);
            return null;
        }
    }

    static RandomAccessFile file = null;
    static FileChannel channel = null;

    public static MappedByteBuffer createNewFile(
            String path, String permissions, boolean memoryMapping) {
        try {
            file = new RandomAccessFile(new File(path), permissions);
            FileChannel channel = file.getChannel();
            final MappedByteBuffer mapFile =
                    channel.map(FileChannel.MapMode.READ_WRITE, 0, 10485760);
            return mapFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeMapBuffer() {
        try {
            file.close();
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                file.close();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
