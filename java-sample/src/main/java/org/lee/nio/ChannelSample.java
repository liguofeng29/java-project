package org.lee.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ChannelSample {


    public static void main(String[] args) {
        // map_sample();

        // read_sample();

         charset_list();

        // charset_sample();
    }

    private static void map_sample() {

        File f = new File("./NIO/channel-in.txt");
        try (
            FileChannel channelIn = new FileInputStream(f).getChannel();
            FileChannel channelOut = new FileOutputStream("./NIO/channel-out.txt").getChannel()) {

            // map()
            MappedByteBuffer mappedByteBuffer = channelIn.map(
                FileChannel.MapMode.READ_ONLY, 0, f.length()
            );

            System.out.println("----channel#map----");
            System.out.println("buffer capacity : " + mappedByteBuffer.capacity());
            System.out.println("buffer limit : " + mappedByteBuffer.limit());
            System.out.println("buffer position : " + mappedByteBuffer.position());
            System.out.println("channel position : " + channelIn.position());

            // write
            channelOut.write(mappedByteBuffer);

            System.out.println("----channel#write----");
            System.out.println("buffer capacity : " + mappedByteBuffer.capacity());
            System.out.println("buffer limit : " + mappedByteBuffer.limit());
            System.out.println("buffer position : " + mappedByteBuffer.position());
            System.out.println("channel position : " + channelOut.position());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private static void read_sample() {

        File f = new File("./NIO/channel-in.txt");
        try (
            FileChannel inChannel = new FileInputStream(f).getChannel()) {

            ByteBuffer byteBuffer = ByteBuffer.allocate(8);
            int hasRead = 0;

            System.out.println("----channel#read１回目----");
            hasRead = inChannel.read(byteBuffer);

            System.out.println("buffer capacity : " + byteBuffer.capacity());
            System.out.println("buffer limit : " + byteBuffer.limit());
            System.out.println("buffer position : " + byteBuffer.position());
            System.out.println("channel position : " + inChannel.position());
            System.out.println("hasRead : " + hasRead);

            byteBuffer.clear();

            System.out.println("----channel#read２回目----");
            hasRead = inChannel.read(byteBuffer);

            System.out.println("buffer capacity : " + byteBuffer.capacity());
            System.out.println("buffer limit : " + byteBuffer.limit());
            System.out.println("buffer position : " + byteBuffer.position());
            System.out.println("channel position : " + inChannel.position());
            System.out.println("hasRead : " + hasRead);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


    }

    private static void charset_list() {
//        Charset.availableCharsets().entrySet().stream()
//            .forEach(System.out::println);

        Charset.availableCharsets().entrySet().forEach(System.out::println);

        System.out.println("default charset : " + Charset.defaultCharset());

    }
    private static void charset_sample() {
        File f = new File("./NIO/file-sjis-in.txt");
        try (
            FileChannel inChannel = new FileInputStream(f).getChannel()) {

            // FileChannel to ByteBuffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            inChannel.read(byteBuffer);
            byteBuffer.flip();

            // Shift_JIS
            Charset sjis = Charset.forName("Shift_JIS");

            // decode buff with SJIS
            CharBuffer charBuffer = sjis.decode(byteBuffer);

            System.out.println("buffer capacity : " + charBuffer.capacity());
            System.out.println("buffer limit : " + charBuffer.limit());
            System.out.println("buffer position : " + charBuffer.position());

            System.out.println("str : " + charBuffer.toString());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (
            FileChannel outChannel = new FileOutputStream("./NIO/file-sjis-out.txt").getChannel()) {

            // unicode
            String str = "123あいう" + System.lineSeparator() + "ＳＨＩＦＴ－ＪＩＳ";

            // Shift_JIS
            Charset sjis = Charset.forName("Shift_JIS");
            // encode buff with SJIS
            ByteBuffer byteBuffer = sjis.encode(str);
            // write to file
            outChannel.write(byteBuffer);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
