package channelsoft.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}
