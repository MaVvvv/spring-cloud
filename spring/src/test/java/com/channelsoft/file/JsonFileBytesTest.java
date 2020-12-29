package com.channelsoft.file;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * json文件流字节数组测试类
 *
 * @author Ma_wei
 * @since 2020/8/26 21:02
 */
public class JsonFileBytesTest {

    @Test
    public void testFileBytes() throws IOException {
        File outFile = new File("D:\\doc\\tmp\\copy\\test.jpg");

        File file = ResourceUtils.getFile("D:\\doc\\tmp\\test.jpg");

        byte[] byte1s = FileUtils.readFileToByteArray(file);
        System.out.println(byte1s.length);
        // 压缩
        byte[] gByte1s = gZip(byte1s);
        System.out.println(gByte1s.length);

        String jpg = new String(Base64.encodeBase64(gByte1s));
        System.out.println(jpg);
        byte[] byte2s = Base64.decodeBase64(jpg);

        byte[] gByte2s = unGZip(byte2s);
        FileUtils.writeByteArrayToFile(outFile,gByte2s);
    }

    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public static byte[] unGZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buf = new byte[1024];
            int num = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            b = baos.toByteArray();
            baos.flush();
            baos.close();
            gzip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
}
