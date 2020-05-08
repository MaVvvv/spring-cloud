package com.mxw.hxb.springboot.spring;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-10 09:53
 */
public class Program {

    public static void main(String[] args) {
        // 实例化鬼剑士
        Swordsman swordsman = new Swordsman();
        swordsman.select(new Up());
        swordsman.hit();
        //
        swordsman.select(new Kill());
        swordsman.hit();

        swordsman.select(new Knock());
        swordsman.hit();
    }
}
