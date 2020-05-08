package com.channelsoft.druid.test;

import com.channelsoft.druid.dto.UserDTO;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-03-27 11:29
 */
public class Test1 {

    private static final String NAME = "TEST1";

    private UserDTO userDTO;

    @Test
    public void test01() {
        userDTO = new UserDTO();
        userDTO.setId("1");
        userDTO.setBooks(Lists.newArrayList("book1","book2"));
        System.out.println(ClassLayout.parseInstance(Test1.class).toPrintable());
    }
}
