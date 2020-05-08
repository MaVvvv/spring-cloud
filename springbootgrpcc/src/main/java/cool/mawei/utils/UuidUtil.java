package cool.mawei.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * 获取UUID工具类
 *
 * @author Ma_wei
 * @since 2018-11-23 16:20
 */
public class UuidUtil {

    /** 日志*/
    private static Logger log = LoggerFactory.getLogger(UuidUtil.class);

    /** 字符集*/
    private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"
    };

    /**
     * 获取短UUID生成（4位）去"-"
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/11/23
     */
    public static String getUUIDfor4Bit() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 4; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取短UUID生成（8位）去"-"
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/11/23
     */
    public static String getUUIDfor8Bit() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取短UUID生成（16位）去"-"
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/11/23
     */
    public static String getUUIDfor16Bit() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        String uuid = uuid1 + uuid2;
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取短UUID生成（24位）去"-"
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/11/23
     */
    public static String getUUIDfor24Bit() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid1 = UUID.randomUUID().toString().replace("-", "");
        String uuid2 = UUID.randomUUID().toString().replace("-", "");
        String uuid3 = UUID.randomUUID().toString().replace("-", "");
        String uuid = uuid1 + uuid2 + uuid3;
        for (int i = 0; i < 24; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取默认32位去"-" uuid
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/11/23
     */
    public static String getDefaultUUID () {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
