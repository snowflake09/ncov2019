package com.utils;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtil
{
    
    private static final String RSA_ECB_PADDING = "RSA/ECB/PKCS1Padding";
     private static final String RSA = "RSA";
    private static final String PUBLIC_KEY =
        "";
    
    private static final String PRIVATE_KEY =
        "";
    
    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /** */
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    // change key to String
    @SuppressWarnings("unused")
    private static String getKeyString(Key key)
    {
        return Base64.encode(key.getEncoded());
    }
    
    // change String to key
    private static PublicKey getPublicKey(String keyStr)
        throws Exception
    {
        return KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(Base64.decode(keyStr)));
    }
    
    // change String to key
    private static PrivateKey getPrivateKey(String keyStr)
        throws Exception
    {
        return KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(keyStr)));
    }
    
    // 加密
    private static byte[] encrypt(Key key, byte[] obj, int offSet, int endSet)
    {
        if (key != null)
        {
            try
            {
//               Cipher cipher = Cipher.getInstance(RSA);    //liuyue
                Cipher cipher = Cipher.getInstance(RSA_ECB_PADDING);      //Android 客户端使用 RSA_ECB_PADDING = "RSA/ECB/PKCS1Padding";
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return cipher.doFinal(obj, offSet, endSet);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    // 解密
    private static byte[] decrypt(Key key, byte[] obj, int offSet, int endSet)
    {
        if (key != null)
        {
            try
            {
//                Cipher cipher = Cipher.getInstance(RSA);  //liuyue
                Cipher cipher = Cipher.getInstance(RSA_ECB_PADDING);  //Android 客户端使用 RSA_ECB_PADDING = "RSA/ECB/PKCS1Padding";
                cipher.init(Cipher.DECRYPT_MODE, key);
                return cipher.doFinal(obj, offSet, endSet);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * 服务端加密
     * @param value
     * @return
     * @author LiQiang
     * @date 2016年11月24日
     */
    public static String serverEncrypt(String value)
    {
        if (RSAUtil.isEmpty(value))
        {
            return null;
        }
        try
        {
            RSAPrivateKey privateKey = (RSAPrivateKey)getPrivateKey(PRIVATE_KEY);
            value = java.net.URLEncoder.encode(value, "utf-8");
            byte[] data = value.getBytes();
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0)
            {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
                {
                    cache = encrypt(privateKey, data, offSet, MAX_ENCRYPT_BLOCK);
                }
                else
                {
                    cache = encrypt(privateKey, data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] enByte = out.toByteArray();
            out.close();
            return Base64.encode(enByte);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 服务端解密
     * @param value
     * @return
     * @author LiQiang
     * @date 2016年11月24日
     */
    public static String serverDecrypt(String value)
    {
        if (RSAUtil.isEmpty(value))
        {
            return null;
        }
        try
        {
            RSAPrivateKey privateKey = (RSAPrivateKey)getPrivateKey(PRIVATE_KEY);
            byte[] enBytes = Base64.decode(value);
            int inputLen = enBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0)
            {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK)
                {
                    cache = decrypt(privateKey, enBytes, offSet, MAX_DECRYPT_BLOCK);
                }
                else
                {
                    cache = decrypt(privateKey, enBytes, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] deByte = out.toByteArray();
            out.close();
            return java.net.URLDecoder.decode(new String(deByte), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 客户端加密
     * @param value
     * @return
     * @author LiQiang
     * @date 2016年11月24日
     */
    public static String clientEncrypt(String value)
    {
        if (RSAUtil.isEmpty(value))
        {
            return null;
        }
        try
        {
            RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(PUBLIC_KEY);
            value = java.net.URLEncoder.encode(value, "utf-8");
            byte[] data = value.getBytes();
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offSet > 0)
            {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK)
                {
                    cache = encrypt(publicKey, data, offSet, MAX_ENCRYPT_BLOCK);
                }
                else
                {
                    cache = encrypt(publicKey, data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] enByte = out.toByteArray();
            out.close();
            return Base64.encode(enByte);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 客户端解密
     * @param value
     * @return
     * @author LiQiang
     * @date 2016年11月24日
     */
    public static String clientDecrypt(String value)
    {
        if (RSAUtil.isEmpty(value))
        {
            return null;
        }
        try
        {
            RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(PUBLIC_KEY);
            byte[] enBytes = Base64.decode(value);
            int inputLen = enBytes.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offSet > 0)
            {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK)
                {
                    cache = decrypt(publicKey, enBytes, offSet, MAX_DECRYPT_BLOCK);
                }
                else
                {
                    cache = decrypt(publicKey, enBytes, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] deByte = out.toByteArray();
            out.close();
            return java.net.URLDecoder.decode(new String(deByte), "utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean isEmpty(String str)
    {
        return ((str == null) || (str.length() == 0) || ("".equals(str)) || ("null".equals(str)));
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
		String server_a = RSAUtil.serverEncrypt("123456");
		System.out.println(server_a);
		String client_a = RSAUtil.clientDecrypt(server_a);
		System.out.println(client_a);
		
		String client_b = RSAUtil.clientEncrypt("liqiang");
		System.out.println(client_b);
		String server_b = RSAUtil.serverDecrypt(client_b);
		System.out.println(server_b);
	}
}