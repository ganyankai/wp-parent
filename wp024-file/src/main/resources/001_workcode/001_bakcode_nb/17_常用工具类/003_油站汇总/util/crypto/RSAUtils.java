package com.catt.common.util.crypto;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加密解密工具类
 */
public final class RSAUtils {

	/**
	 * 安全服务提供者
	 */
	private static final Provider provider = new BouncyCastleProvider();

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 密钥大小
	 */
	private static final int KEY_SIZE = 1024;

	/**
	 * 不可实例化
	 */
	private RSAUtils () {}

	/**
	 * 生成密钥对
	 * @return
	 */
	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", provider);
			keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * @param publicKey	公钥
	 * @param data		数据
	 * @return
	 */
	public static byte[] encrypt(PublicKey publicKey, byte[] data) {
		Assert.notNull(publicKey);
		Assert.notNull(data);

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			return cipher.doFinal(data);

            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();

            return encryptedData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 * @param publicKey	公钥
	 * @param text		字符串
	 * @return
	 */
	public static String encrypt(PublicKey publicKey, String text) {
		Assert.notNull(publicKey);
		Assert.notNull(text);

		byte[] data = encrypt(publicKey, text.getBytes());
		return data != null ? Base64.encodeBase64String(data) : null;
	}

	/**
	 * 解密
	 * @param privateKey	私钥
	 * @param encryptedData			数据
	 * @return
	 */
	public static byte[] decrypt(PrivateKey privateKey, byte[] encryptedData) {
		Assert.notNull(privateKey);
		Assert.notNull(encryptedData);

		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
//			return cipher.doFinal(data);

            int inputLen = encryptedData.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher
                            .doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher
                            .doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();

            return decryptedData;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param privateKey	私钥
	 * @param text			字符串
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String decrypt(PrivateKey privateKey, String text) {
		String result = null;
		
		Assert.notNull(privateKey);
		Assert.notNull(text);

		try {
			byte[] data = decrypt(privateKey, Base64.decodeBase64(text));
			result = data != null ? new String(data, "UTF-8") : null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 得到密钥字符串（经过base64编码）
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getKeyString(Key key) throws Exception {
		byte[] keyBytes = key.getEncoded();
		String publickey = (new BASE64Encoder()).encode(keyBytes);
		return publickey;
	}

	/**
	 * 根据Base64公钥字符串生成公钥对象
	 * @param pubKey Base64公钥字符串
	 * @return PublicKey 公钥对象
	 * @throws InvalidKeySpecException 异常
	 * @throws IOException 异常
	 * @throws NoSuchAlgorithmException 异常
     */
	public static PublicKey genPublicKey(String pubKey) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(new BASE64Decoder().decodeBuffer(pubKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(bobPubKeySpec);
	}

	/**
	 * 根据Base64私钥字符串生成私钥对象
	 * @param priKey Base64私钥字符串
	 * @return PrivateKey 私钥对象
	 * @throws InvalidKeySpecException 异常
	 * @throws IOException 异常
	 * @throws NoSuchAlgorithmException 异常
     */
	public static PrivateKey genPrivateKey(String priKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(priKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(priPKCS8);
	}
}
