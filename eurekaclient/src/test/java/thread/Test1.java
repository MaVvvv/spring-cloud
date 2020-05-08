package thread;

import dto.Reordering;
import org.junit.Test;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-19 15:28
 */
public class Test1 {

    @Test
    public void testThread () {
        Reordering reordering = new Reordering();
        Thread t1 = new Thread(() -> reordering.writer());
        Thread t2 = new Thread(() -> {
            reordering.read();
        });
        t1.start();
        t2.start();
    }
}
