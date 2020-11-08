package cn.dante.t05nio;

import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.toString());

        byte array[] = new byte[1024];
        ByteBuffer buffer = ByteBuffer.wrap(array);
    }

}
