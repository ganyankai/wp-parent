package cn.dante.t02unsignedbyte;

public class UnsignedByte {
    public Short getValue(byte i){      //将byte转换为无符号的数字
        short li = (short) (i & 0xff);
        return li;
    }

    public byte toUnsignedByte(short i){   //将short转换为无符号的byte
        return (byte) (i & 0xff);
    }

}
