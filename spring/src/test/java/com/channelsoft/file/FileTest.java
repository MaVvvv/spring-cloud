package channelsoft.file;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.zip.*;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-29 10:11
 */
public class FileTest {

    @Test
    public void testClassLoader() throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String webXmlPath = Objects.requireNonNull(
                classLoader.getResource("WEB-INF/web.xml")
        ).getPath();
        LineIterator lineIterator = FileUtils.lineIterator(new File(webXmlPath));
        lineIterator.forEachRemaining(System.out::println);
    }

    @Test
    public void testResourceUtils() throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "WEB-INF/web.xml");
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        lines.forEach(System.out::println);
    }

    @Test
    public void testFileUtilsWrites() throws IOException {
        String path = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
        System.out.println(path);
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "WEB-INF/web.xml");
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        FileUtils.writeLines(new File(path,"new-web.xml"),lines);
        FileUtils.copyFile(file,new File(path,"new-web1.xml"));
    }

    @Test
    public void testFileBandle() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("applicationContext");
        System.out.println(resourceBundle.getString("spring-bean"));
    }

    @Test
    public void testCopyFile() throws IOException {
        System.out.println(System.getProperties().getProperty("file.encoding"));
        File outFile = new File("D:\\doc\\tmp\\copy\\test.jpg");

        File file = ResourceUtils.getFile("D:\\doc\\tmp\\test.jpg");
        //FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        System.out.println(bytes.length);

        FileUtils.writeByteArrayToFile(outFile,bytes);
        /*//
        this.uploadFile(outFile,inputStream);

        List<String> lines = FileUtils.readLines(outFile);
        System.out.println(Charset.defaultCharset());
        lines.forEach(line -> {
            try {
                System.out.println("UTF-8 =====" + line);
                byte[] bytes = line.getBytes("GBK");
                String gbkStr = new String(bytes,"GBK");
                System.out.println("GBK ====" + gbkStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });*/


        //InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(file),Charset.forName("GBK"));
        //FileUtils.copyInputStreamToFile(new FileInputStream(file),new File("D:\\tmp\\dcmsdialer\\calllist\\copy\\new_dialer.txt"));
        //FileUtils.copyFileToDirectory(file,new File("D:\\tmp\\dcmsdialer\\calllist\\copy"));
    }

    public void uploadFile(File destFile, InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile), Charset.defaultCharset()));
        byte[] b = new byte[1024];
        String line = null;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.write("\r\n");
        }
        writer.close();
        reader.close();
    }

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

    /***
     * 压缩Zip
     *
     * @param data
     * @return
     */
    public static byte[] zip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zip = new ZipOutputStream(bos);
            ZipEntry entry = new ZipEntry("zip");
            entry.setSize(data.length);
            zip.putNextEntry(entry);
            zip.write(data);
            zip.closeEntry();
            zip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /***
     * 解压Zip
     *
     * @param data
     * @return
     */
    public static byte[] unZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(bis);
            while (zip.getNextEntry() != null) {
                byte[] buf = new byte[1024];
                int num = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }
                b = baos.toByteArray();
                baos.flush();
                baos.close();
            }
            zip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    /**
     * 对byte[]进行压缩
     *
     * @param 要压缩的数据
     * @return 压缩后的数据
     */
    public static byte[] compress(byte[] data) {
        System.out.println("before:" + data.length);

        GZIPOutputStream gzip = null ;
        ByteArrayOutputStream baos = null ;
        byte[] newData = null ;

        try {
            baos = new ByteArrayOutputStream() ;
            gzip = new GZIPOutputStream(baos);

            gzip.write(data);
            gzip.finish();
            gzip.flush();

            newData = baos.toByteArray() ;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                gzip.close();
                baos.close() ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("after:" + newData.length);
        return newData ;
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

}
