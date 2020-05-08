package thread;

import dto.PersonDTO;
import dto.PersonDTO1;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-09 15:04
 */
public class Thread3 implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            PersonDTO1 personDTO = (PersonDTO1) MainThread.queue.take();
            System.out.println(Thread.currentThread().getName() + " Age = " + personDTO.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
