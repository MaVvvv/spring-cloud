package com.channelsoft.thread;

import com.google.common.collect.Lists;

/**
 * 模拟抢票线程
 *
 * @author Ma_wei
 * @since 2020-04-30 11:19
 */
public class BuyTicketThread implements Runnable{

    private int ticketNum = 10;

    private boolean flag = true;

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (flag) {
            buy();
        }
    }

    private void buy() {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }
        System.out.println(Thread.currentThread().getName() + "抢到了" + ticketNum --);
    }
}
