package com.mxw.hxb.springboot.spring;

import com.mxw.hxb.springboot.spring.abs.AbstractSkill;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-10 10:08
 */
public class NewSwordsman {

    private AbstractSkill skill;

    public void select(AbstractSkill skill) {
        this.skill = skill;
    }

    public void hit() {
        skill.speak();
    }
}
