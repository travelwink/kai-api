//package com.travelwink.kai.framework.utils;
//
//import com.travelwink.kai.framework.properties.ShiroCryptoProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.apache.shiro.lang.util.ByteSource;
//import org.apache.shiro.lang.util.SimpleByteSource;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class ShiroCryptoUtil {
//
//    private static ShiroCryptoProperties shiroCryptoProperties;
//
//    public ShiroCryptoUtil(ShiroCryptoProperties shiroCryptoProperties) {
//        ShiroCryptoUtil.shiroCryptoProperties = shiroCryptoProperties;
//    }
//
//    /**
//     * 生成随机盐值
//     * @return string of salt
//     */
//    public static String generateSalt() {
//        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
//        ByteSource salt = randomNumberGenerator.nextBytes(16);
//        log.info("Generated salt is {}", salt.toHex());
//        return salt.toHex();
//    }
//
//    /**
//     * 密码加盐哈希加密
//     * @param password 密码
//     * @param salt 盐
//     * @return hashed password string
//     */
//    public static String encryptPassword(String password, String salt) {
//        String algorithm = shiroCryptoProperties.getHashAlgorithm();
//        int hashIterations = shiroCryptoProperties.getHashIterations();
//        String encryptedPassword = new SimpleHash(algorithm, password, ByteSource.Util.bytes(salt), hashIterations).toHex();
//        log.info("Encrypted password is {}", encryptedPassword);
//        return encryptedPassword;
//    }
//
//    /**
//     * 验证密码
//     * @param password 待验证密码
//     * @param salt 盐
//     * @param actualPassword 实际密码
//     * @return 验证结果
//     */
//    public static boolean verifyPassword(String password, String salt, String actualPassword) {
//        boolean result = encryptPassword(password, salt).equals(actualPassword);
//        log.info("Password match result is {}", result);
//        return result;
//    }
//
//    /**
//     * 增强并包装盐值
//     * @param systemSecret 系统配置密钥
//     * @param userSalt 数据库中存储的用户盐值
//     * @return 重新包装后的盐值字符串
//     */
//    public static String enhanceAndWrapSalt(String systemSecret, String userSalt) {
//        return new SimpleByteSource(systemSecret + userSalt).toHex();
//    }
//
//}
