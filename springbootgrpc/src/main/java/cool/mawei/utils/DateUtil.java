package cool.mawei.utils;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间相关工具类
 * <依赖commons.lang.Jar包></依赖commons.lang.Jar包>
 *
 * @author Ma_wei
 * @since 2019-01-16 11:54
 */
public class DateUtil {

    /**
     * 返回mysql数据类型dateTime的系统当前时间
     * <yyyy-MM-dd HH:mm:ss>
     *
     * @param
     * @return Timestamp
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static Timestamp getCurrentDateTime (){
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 返回mysql数据类型dateTime的系统当前时间
     * <format>
     *
     * @param
     * @return Timestamp
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static Timestamp getCurrentDateTime (String format){
        return Timestamp.valueOf(new SimpleDateFormat(format).format(new Date()));
    }

    /**
     * 返回默认当前时间字符串
     * <yyyy-MM-dd HH:mm:ss>
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static String getCurrentDate (){
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回指定格式时间字符串
     *
     * @param format
     * @return String
     * @author Ma_wei
     * @since 2019/5/21
     */
    public static String getCurrentDate (@NotNull @NotEmpty String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * 返回当前AM PM时间字符串
     * <KK:mm:ss AM/PM>
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2019/5/21
     */
    public static String getCurrentEnglishDate () {
        return getCurrentEnglishDate("KK:mm:ss aa");
    }

    /**
     * 返回当前AM PM时间字符串
     * <KK:mm:ss AM/PM>
     *
     * @param format
     * @return String
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static String getCurrentEnglishDate (@NotNull @NotEmpty String format){
        return new SimpleDateFormat(format, Locale.ENGLISH).format(new Date());
    }

    /**
     * 返回当前中国时间字符串
     * <KK:mm:ss 上午/下午>
     *
     * @param
     * @return String
     * @author Ma_wei
     * @since 2019/5/21
     */
    public static String getCurrentChineseDate () {
        return getCurrentChineseDate("KK:mm:ss aa");
    }

    /**
     * 返回当前中国时间字符串
     * <KK:mm:ss 上午/下午>
     *
     * @param format
     * @return String
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static String getCurrentChineseDate (@NotNull @NotEmpty String format){
        return new SimpleDateFormat(format, Locale.CHINESE).format(new Date());
    }

    /**
     * 按照时间格式获取时间
     *
     * @param date,format
     * @return String
     * @author Ma_wei
     * @since 2019/5/28
     */
    public static String transformDate2Str (@NotNull Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 返回当前时间字符串
     * <yyyy-MM-dd HH:mm:ss>
     *
     * @param date
     * @return String
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static String transformDate2Str (@NotNull Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 格式化返回mysql数据类型dateTime的系统当前时间
     * <yyyy-MM-dd HH:mm:ss>
     *
     * @param date
     * @return Timestamp
     * @author Ma_wei
     * @since 2018/8/22
     */
    public static Timestamp transformDate2DateTime (@NotNull Date date){
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    /**
     * 获取当前时间戳
     *
     * @param
     * @return long
     * @author Ma_wei
     * @since 2019/1/23
     */
    public static long getCurrentTimeMillis () {
        return System.currentTimeMillis();
    }

    /**
     * 将毫秒级时间戳转化为特定时间格式
     *
     * @param msec
     * @return String
     * @author Ma_wei
     * @since 2019/3/19
     */
    public static String transformDate2Str (@NotNull @Min(0) Long msec) {
        Date date = new Date(msec);
        return transformDate2Str(date);
    }

    /**
     * 将毫秒级时间戳转化为特定时间格式
     *
     * @param msec,format
     * @return String
     * @author Ma_wei
     * @since 2019/3/19
     */
    public static String transformDate2Str (@NotNull @Min(0) Long msec, @NotNull @NotEmpty String format) {
        Date date = new Date(msec);
        return transformDate2Str(date,format);
    }


    /**
     * 时间戳转日期类型
     *
     * @param  msec
     * @return Date
     * @author Ma_wei
     * @since 2019/9/2
     */
    public static Date transformTimestamp2Date(@NotNull @Min(0) Long msec) {
        return new Date(msec);
    }

    /**
     * 解析时间串为时间戳格式
     *
     * @param dateStr
     * @param format
     * @return long
     * @author Ma_wei
     * @since 2019/9/10
     */
    public static long parseDateStr2Timestamp(String dateStr, String format) throws ParseException {
        Date date = new SimpleDateFormat(format).parse(dateStr);
        return date.getTime();
    }

    /**
     * 解析时间串为时间格式
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return long
     * @author Ma_wei
     * @since 2019/9/10
     */
    public static String parseDateStr2DateFormat(String dateStr, String srcFormat, String targetFormat) throws ParseException {
        Date date = new SimpleDateFormat(srcFormat).parse(dateStr);
        return new SimpleDateFormat(targetFormat).format(date);
    }
}
