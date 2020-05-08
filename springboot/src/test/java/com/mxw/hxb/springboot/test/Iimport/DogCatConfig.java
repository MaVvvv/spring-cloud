package com.mxw.hxb.springboot.test.Iimport;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-04 11:50
 */
@Import({Dog.class,Cat.class})
@SpringBootConfiguration
public class DogCatConfig {
}
