package thread;

import dto.BaseDTO;
import dto.PersonDTO;
import dto.PersonDTO1;
import dto.PersonDTO2;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-09 15:04
 */
public class MainThread {

    public static PersonDTO personDTO = new PersonDTO();

    public static ArrayBlockingQueue<BaseDTO> queue = new ArrayBlockingQueue<>(10);

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    static {

    }

    public static void main(String[] args) throws Exception {
        BaseDTO person1 = new PersonDTO();
        ((PersonDTO) person1).setAge(0);
        BaseDTO person2 = new PersonDTO1();
        ((PersonDTO1) person2).setAge(1);
        BaseDTO person3 = new PersonDTO2();
        ((PersonDTO2) person3).setAge(2);
        queue.put(person1);
        queue.put(person2);
        queue.put(person3);
        scheduledExecutorService.execute(new Thread2());
        scheduledExecutorService.execute(new Thread1());
        scheduledExecutorService.execute(new Thread3());
    }
}
