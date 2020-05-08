package thread;

import dto.PersonDTO;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-09 15:04
 */
public class Thread2 implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            PersonDTO personDTO = (PersonDTO)MainThread.queue.take();
            System.out.println(Thread.currentThread().getName() + " Age = " + personDTO.getAge());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
