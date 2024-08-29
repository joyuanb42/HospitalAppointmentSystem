package zyb.design.ssm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;

public class MD5AndSalt {
    private static final String ALGORITHM_NAME = "MD5";
    private static final int HASH_ITERATIONS = 1;
    //加密密码
    public static String encryptPassword(String password, String salt) {
        SimpleHash hash = new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(salt), HASH_ITERATIONS);
        return hash.toHex();
    }
    //生成随机盐值
    public static String generateSalt() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
