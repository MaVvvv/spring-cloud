package com.hxb.jdk8.service;

import java.io.*;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-12-20 17:48
 */
public class EncryptT1 {
    public static void main(String[] args) throws IOException {
        secret("D:\\CloudMusic\\林俊杰,金莎 - 被风吹过的夏天.mp3");
        decrypt("D:\\CloudMusic\\out\\marden1.mp3");
    }

    //加密
    public static void secret(String str) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(str));
        String [] s=str.split("\\.");
        BufferedOutputStream bos = new BufferedOutputStream(new
                FileOutputStream("D:\\CloudMusic\\out\\marden1."+s[1]));
        int n;
        long a=System.currentTimeMillis();
        while((n=bis.read())!=-1){
            bos.write(n + 999999);
        }
        long b=System.currentTimeMillis();
        bis.close();
        bos.close();
        System.out.println("加密拷贝成功！");
        System.out.println("加密用时："+(b-a)+"ms");
    }


    //解密
    public static void decrypt(String str) throws IOException{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(str));
        String [] s=str.split("\\.");
        BufferedOutputStream bos = new BufferedOutputStream(new
                FileOutputStream("D:\\CloudMusic\\out\\marden2."+s[1]));
        int n;
        long a=System.currentTimeMillis();
        while((n=bis.read())!=-1){
            bos.write(n-999999);
        }
        long b=System.currentTimeMillis();
        bis.close();
        bos.close();
        System.out.println("解密拷贝成功！");
        System.out.println("解密用时："+(b-a)+"ms");
    }

}
