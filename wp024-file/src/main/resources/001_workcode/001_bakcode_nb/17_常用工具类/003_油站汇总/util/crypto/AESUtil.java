package com.catt.common.util.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密工具类
 */
public class AESUtil {

    /**
     * 生成128bit密钥
     *
     * @return
     */
    public static byte[] genKey() {
        try {
            //密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //默认128，获得无政策权限后可为192或256
            keyGenerator.init(128);
            //生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            //密钥字节数组
            byte[] encoded = secretKey.getEncoded();
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CBC模式加密
     *
     * @param content     明文数据
     * @param key         密钥
     * @param iv          初始向量，必须16字节
     * @param paddingMode 填充模式
     * @return 密文数据
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] CBCEncrypt(byte[] content, byte[] key, byte[] iv, PaddingMode paddingMode)
            throws BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(key, "AES");//恢复密钥
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/" + paddingMode.name());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cipher.doFinal(content);
    }

    /**
     * CBC模式解密
     *
     * @param content     密文数据
     * @param key         密钥
     * @param iv          初始向量，必须16字节
     * @param paddingMode 填充模式
     * @return 明文数据
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] CBCDecrypt(byte[] content, byte[] key, byte[] iv, PaddingMode paddingMode)
            throws BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = new SecretKeySpec(key, "AES");//恢复密钥
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/" + paddingMode.name());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return cipher.doFinal(content);
    }

    /**
     * 填充方式
     */
    public enum PaddingMode {
        NoPadding,
        PKCS5Padding,
        ISO10126Padding;
    }

}


