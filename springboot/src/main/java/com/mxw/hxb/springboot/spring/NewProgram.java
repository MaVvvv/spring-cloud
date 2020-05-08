package com.mxw.hxb.springboot.spring;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-10 10:10
 */
public class NewProgram {

    public static void main(String[] args) {
        NewSwordsman newSwordsman = new NewSwordsman();
        newSwordsman.select(new Kill());
        newSwordsman.hit();

        newSwordsman.select(new Up());
        newSwordsman.hit();

        newSwordsman.select(new Knock());
        newSwordsman.hit();
    }
}
