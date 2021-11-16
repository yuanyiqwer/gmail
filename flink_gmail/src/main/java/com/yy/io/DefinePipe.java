package com.yy.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @ClassName DefinePipe
 * @Author yy
 * @Description
 * @Date 2021/9/15 14:00
 * @Version 1.0
 **/
public class DefinePipe {

    public static void main(String[] args) {

        try {
            final Pipe pip = Pipe.open();
            try (final Pipe.SourceChannel source = pip.source();
                 final Pipe.SinkChannel sink = pip.sink();) {

                final ByteBuffer write = ByteBuffer.wrap(new byte[1024]);
                write.put("ssss".getBytes());
                write.flip();
                sink.write(write);
                final ByteBuffer read = ByteBuffer.allocate(1024);
                source.read(read);
                System.out.println(new String(read.array()));


            } catch (Exception e) {
            } finally {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
