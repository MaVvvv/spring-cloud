package com.hxb.jdk8.Test;

import com.google.common.collect.Collections2;
import org.assertj.core.util.Lists;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-20 10:56
 */
public class TestLambda {

    private static final Logger log = LoggerFactory.getLogger(TestLambda.class);

    ExecutorService executorService;

    @Test
    public void test01() {
        log.debug("*************  JDK 1.7 *****************");
        Runnable r7 = new Runnable() {
            @Override
            public void run() {
                log.info("学习1.7语法~");
            }
        };

        r7.run();
        log.debug("*************  JDK 1.8 Lambda *****************");
        Runnable r8 = () -> {
            log.info("学习1.8语法~");
        };
        r8.run();
    }

    @Test
    public void test02() {
        log.debug("************ 类型推断 *************");
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                log.info(s);
            }
        };

        log.debug("**************lambda*************");
        Consumer<String> con1 = (String s) -> {
            log.info(s);
        };
        con1.accept("con1传递参数！");
        Consumer<String> con2 = (s) -> {
            log.info(s);
        };
        con2.accept("con2传递参数！");
    }

    @Test
    public void test03 () {
        log.debug("********** jdk 1.7 *************");
        List<Integer> list = Arrays.asList(15,5,3,2,14,79);
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });
        log.debug("list = {}",list);
        log.debug("********** jdk 1.8 *************");
        List<Integer> list1 = Arrays.asList(15,5,3,2,14,79);
        //Collections.sort(list1,(o1,o2) -> {return o1 - o2;});
        Collections.sort(list1,(o1,o2) -> o1 - o2);
        log.info("list1 = {}",list1);
    }

    @Test
    public void test04 () {
        System.out.println("****************  JDK 1.7 比较函数   ********************");
        Comparator<Integer> comp1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare = comp1.compare(10, 20);
        System.out.println(compare);

        System.out.println("****************  JDK 1.8 比较函数   ********************");
        System.out.println("****************    Lambda 表达式    ********************");
        // Lambda 表达式
        Comparator<Integer> comp2 =  (o1, o2) ->Integer.compare(o1,o2);
        int comparator1 = comp2.compare(10,20);
        System.out.println(comparator1);

        System.out.println("****************    Lambda 方法引用  ********************");
        //  Lambda 方法引用
        Comparator<Integer>  comp3  =  Integer::compare;
        int compare1 = comp3.compare(10, 20);
        System.out.println(compare1);

    }

    private static String str1 () {
        return "str1";
    }

    @Test
    public void test05() {
        /*Stream<Integer> integerStream = Stream.of(1,2,3,4,5,6);
        integerStream.forEach(System.out::println);
        integerStream.close();*/
        Predicate<String> p = o -> o.equals("test");
        System.out.println(p.test("tesst"));
    }

    @Test
    public void test06 () {
        Object str = null;
        if (str instanceof String) {
            log.debug("String");
        } else if (str instanceof Integer) {
            log.debug("Integer");
        } else {
            log.debug("Null");
        }

    }

    @Test
    public void test07 () {
        List<String> list = Lists.newArrayList("1","2","4","8","2","4");
        Stream<String> stringStream = list.stream();
        Stream<String> distinct = stringStream.distinct();
        distinct.forEach(log::info);
        Stream<String> limit = list.stream().limit(3);
        limit.forEach(log::info);
        log.warn("-------------------------------");
        Predicate<String> p1 = e -> e.compareToIgnoreCase(String.valueOf(4)) <= 4;
        Stream<String> sorted = list.stream().sorted().limit(4).filter(p1);
        sorted.forEach(log::debug);
        log.warn("-------------------------------");
        Function<String,Integer> function = Integer::parseInt;
        Stream<Integer> map = list.stream().map(function);
        map.forEach(e -> log.info(e.getClass().getSimpleName()));
    }

    @Test
    public void test08 () {
        List<String> list = Lists.newArrayList("1","2","3","4","5","6","7","8","9","10");
        int m = list.stream().mapToInt(Integer::parseInt).sum();
        log.info(String.valueOf(m));
    }

    @Test
    public void test09() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        executor.execute(this::print);
        executor.execute(this::print);
        executor.execute(this::print);
        executor.execute(this::print);
    }

    private void print() {
        log.info(Thread.currentThread().getName() + "---" + System.currentTimeMillis() + "ms");
    }

    private class User {
        private int age;
        private String name;
        private String address;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
