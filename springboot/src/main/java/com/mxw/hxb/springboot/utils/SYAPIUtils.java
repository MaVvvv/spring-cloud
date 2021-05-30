package com.mxw.hxb.springboot.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

/**
 * @Description:加密
 * @author houzi
 * @date May 28, 2019 8:10:07 AM
 */
public class SYAPIUtils {

    private static final String LF = "\n";

    private static final String ENCODING = "UTF-8";

    private static final String HMACSHA256 = "HmacSHA256";

    private static final String HTTP_HEADER_ACCEPT = "Accept";
    private static final String HTTP_HEADER_CONTENT_MD5 = "Content-MD5";
    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HTTP_HEADER_DATE = "Date";

    /**
     * 生成秘钥
     * @param appSecret
     * @param httpMethod
     * @param path
     * @param headers
     * @return
     */
    public static final String sign(String appSecret, String httpMethod, String path, Map<String, String> headers){
        try {
            Mac hmacSha256 = Mac.getInstance(HMACSHA256);
            byte[] keyBytes = appSecret.getBytes(ENCODING);
            hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, HMACSHA256));
            return new String(Base64.getEncoder().encode(
                    hmacSha256.doFinal(buildStringToSign(httpMethod, path, headers).getBytes(ENCODING))), ENCODING);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String buildStringToSign(String method, String path, Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.toUpperCase()).append(LF);
        if (null != headers) {
            if (null != headers.get(HTTP_HEADER_ACCEPT)) {
                sb.append(headers.get(HTTP_HEADER_ACCEPT));
            }
            sb.append(LF);
            if (null != headers.get(HTTP_HEADER_CONTENT_MD5)) {
                sb.append(headers.get(HTTP_HEADER_CONTENT_MD5));
            }
            sb.append(LF);
            if (null != headers.get(HTTP_HEADER_CONTENT_TYPE)) {
                sb.append(headers.get(HTTP_HEADER_CONTENT_TYPE));
            }
            sb.append(LF);
            if (null != headers.get(HTTP_HEADER_DATE)) {
                sb.append(headers.get(HTTP_HEADER_DATE));
            }
        }
        sb.append(LF);
        sb.append(path);
        return sb.toString();
    }
}
