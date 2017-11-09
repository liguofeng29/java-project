package org.lee.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.Buffer;
import java.nio.CharBuffer;

public class BufferSample {


    public static void main(String[] args) {
        demo1();

    }


    private static void demo1() {

        // capacity : buffのサイズ
        // limit : IO出来ない最初のINDEX、INDEX以後のデータはIOできない
        // position : IOする最初のINDEX、IOはpositionの位置から始まる
        // mark :
        // 0 <= mark <= position <= limit <= capacity

        // 生成
        CharBuffer buffer = CharBuffer.allocate(8);
        System.out.println("-----生成後-----");
        showProperty(buffer);

        buffer.put('a');
        buffer.put('b');
        buffer.put('c');
        System.out.println("-----put後-----");
        showProperty(buffer);

        buffer.flip();
        System.out.println("-----flip後-----");
        showProperty(buffer);


        buffer.get();
        buffer.get();
        System.out.println("-----get後-----");
        showProperty(buffer);

        buffer.clear();
        System.out.println("-----clear後-----");
        showProperty(buffer);
        System.out.println("get(2) : " + buffer.get(2));
    }

    private static void showProperty(CharBuffer buffer) {
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("limit : " + buffer.limit());
        System.out.println("position : " + buffer.position());
        System.out.println("toString() : " + buffer.toString());
    }
}



