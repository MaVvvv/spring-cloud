package com.mxw.hxb.springboot.spring;

import com.mxw.hxb.springboot.spring.abs.AbstractSkill;

/**
 * 技能蹦天击
 *
 * @author Ma_wei
 * @since 2019-09-10 09:56
 */
public class Knock extends AbstractSkill {

    @Override
    public void speak() {
        System.out.println("oh yeah!!!");
    }
}
