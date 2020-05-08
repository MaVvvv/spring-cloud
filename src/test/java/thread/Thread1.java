package thread;

import dto.PersonDTO;
import dto.PersonDTO2;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-09 15:04
 */
public class Thread1 implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(150);
            PersonDTO2 personDTO = (PersonDTO2)MainThread.queue.take();
            System.out.println(Thread.currentThread().getName() + " Age = " + personDTO.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
