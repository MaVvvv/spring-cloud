package com.mxw.hxb.springboot.test;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 快递100签名工具类
 *
 * @author Ma_wei
 * @since 2020/10/14 15:02
 */
public class SignUtils {

    /**
     * 快递100加密方式统一为MD5后转大写
     *
     * @param msg
     * @return
     */
    public static String sign(String msg) {
        return DigestUtils.md5Hex(msg).toUpperCase();
    }
}
