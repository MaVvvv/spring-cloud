package com.channelsoft.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-05-19 19:20
 */
public class ForkJoinTest extends RecursiveTask<Long> {

    private Long start;

    private Long end;

    private Long temp = 10000L;

    public ForkJoinTest(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        if ((end - start) <= temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // 拆分任务
            long mid = (start + end )/2;    // 取中间值
            ForkJoinTest forkJoinTest1 = new ForkJoinTest(start,mid);
            // 拆分任务，把任务压入线程队列
            forkJoinTest1.fork();
            ForkJoinTest forkJoinTest2 = new ForkJoinTest(mid + 1,end);
            forkJoinTest2.fork();
            return forkJoinTest1.join() + forkJoinTest2.join();
        }

    }
}
