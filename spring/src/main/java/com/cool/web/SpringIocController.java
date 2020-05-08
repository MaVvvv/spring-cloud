package com.cool.web;

import com.cool.service.ISpringIocService;
import com.cool.utils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-09 19:58
 */
public class SpringIocController {

    //private static final Logger log = LoggerFactory.getLogger(SpringIocController.class);

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        BeanUtils.init(applicationContext);
        // 使用classLoader获取配置文件路径
        String fileUrl = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource("applicationContext.xml")).getPath();
        // commons-io包下的文件工具类
        File fileU = FileUtils.getFile(fileUrl);
        LineIterator lineIterator = FileUtils.lineIterator(fileU);
        lineIterator.forEachRemaining(System.out::println);
        // 使用Spring的ResourceUtils获取文件
        File file = ResourceUtils.getFile("applicationContext.xml");
        System.out.println("ResourceUtils.getFileName() = " + file.getName());
        /*ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        assert classLoader != null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(Objects.requireNonNull(classLoader.getResource("config.properties")).toURI())));
        log.info(bufferedReader.readLine());*/

        System.out.println(ObjectUtils.identityToString(new SpringIocController()));
        //ApplicationContext annoApplicationContext = new AnnotationConfigApplicationContext();
        ISpringIocService springIocService = BeanUtils.getBean("springIocService",ISpringIocService.class);
        ISpringIocService springIocAnService = (ISpringIocService)BeanUtils.getBean("springAnIocService");
    }
}
