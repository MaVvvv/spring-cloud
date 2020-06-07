package channelsoft.file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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
        File outFile = new File("D:\\tmp\\dcmsdialer\\calllist\\copy\\new_dialer.txt");

        File file = ResourceUtils.getFile("D:\\tmp\\dcmsdialer\\calllist\\dialer.txt");
        FileInputStream inputStream = new FileInputStream(file);
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
        });


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
}
