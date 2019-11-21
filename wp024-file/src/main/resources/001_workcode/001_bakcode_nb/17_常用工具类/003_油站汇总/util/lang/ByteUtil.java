package com.catt.common.util.lang;

/**
 * 字节工具类
 */
public class ByteUtil {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hexString 16进制字符串
     * @return
     */
    public static byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }

        if (hexString.length() % 2 != 0) {
            throw new RuntimeException("字符串【" + hexString + "】长度不是偶数，不是合法的十六进制字符串");
        }

        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * 字符转字节
     *
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        int index = "0123456789ABCDEF".indexOf(c);
        if (index == -1) {
            throw new RuntimeException("字符【" + c + "】不是十六进制字符");
        }
        return (byte) index;
    }

}
