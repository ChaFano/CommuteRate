package com.chafan.mvc.utils;

import java.util.Random;

/**
 * @Auther: 茶凡
 * @ClassName SaltUtil
 * @Description TODO
 * @date 2022/5/31 22:53
 * @Version 1.0
 */
public class SaltUtil {

    /**
     * ⽣成salt的静态⽅法
     * @param n
     * @return
     */
    public static String getSalt(int n) {
        StringBuilder sb = new StringBuilder();
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm7894561230!@#$%^&* ()".toCharArray();
        for (int i = 0; i < n; i++) {
            char c = chars[new Random().nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }


}
