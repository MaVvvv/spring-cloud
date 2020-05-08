package com.mxw.hxb.springboot.spring;

import com.mxw.hxb.springboot.spring.abs.AbstractSkill;

/**
 * 鬼斩技能
 *
 * @author Ma_wei
 * @since 2019-09-10 09:45
 */
public class Kill extends AbstractSkill {

    @Override
    public void speak() {
        // 鬼斩音效
        System.out.println("哈！！！");
    }
}
