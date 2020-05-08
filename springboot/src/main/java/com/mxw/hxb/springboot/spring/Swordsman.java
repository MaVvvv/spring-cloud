package com.mxw.hxb.springboot.spring;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-10 09:46
 */
public class Swordsman {

    /** 上调技能*/
    private Up up;

    /** 鬼斩技能*/
    private Kill kill;

    /** 崩山击*/
    private Knock knock;

    public void select (Object skill) {
        if (skill instanceof Up) {
            up = (Up)skill;
            kill = null;
            knock = null;
        } else if (skill instanceof Kill){
            kill = (Kill)skill;
            up = null;
            knock = null;
        } else {
            knock = (Knock)skill;
            up = null;
            kill = null;
        }
    }

    public void hit() {
        System.out.println("开始打怪！！");
        if (kill == null && knock == null) {
            up.speak();
        } else if (up == null && knock == null){
            kill.speak();
        } else {
            knock.speak();
        }
    }
}
