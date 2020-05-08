package com.mxw.hxb.springboot.spring;

import com.mxw.hxb.springboot.spring.abs.AbstractSkill;

import java.io.Console;

/**
 * 上挑 技能
 *
 * @author Ma_wei
 * @since 2019-09-10 09:42
 */
public class Up extends AbstractSkill {

    @Override
    public void speak() {
        /*Console console = new Console();*/
        // 上调音效
        System.out.println("嘿！！！");
    }
}
