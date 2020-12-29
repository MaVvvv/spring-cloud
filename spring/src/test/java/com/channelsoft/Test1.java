package com.channelsoft;

import com.alibaba.excel.EasyExcel;
import com.channelsoft.easyexcel.CellDataDemoHeadDataListener;
import com.channelsoft.easyexcel.Person;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.*;
import java.util.logging.Logger;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-04-20 18:15
 */
public class Test1 {

    private static final Logger log = Logger.getLogger("Test1");

    @Test
    public void Test01() {
        List<String> list1 = new ArrayList<>(2);
        log.info("总长度为：" + list1.size());
        list1.add("a");
        int a = 9;
        int b = a >> 1; // "1001 >> 0100";
        int c = a << 2;
        log.info("右移一位b = " + b);
        log.info("左移一位c = " + c);
    }

    @Test
    public void Test02() {
        HashMap<String,String> map = new HashMap<>();
        int i = (new HashMap<String,String>().size() == 1 ? new HashMap<>(2):new HashMap<>(0)).size();
        String a = null;
        //log.info("hoshCode = " + a.hashCode());

        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("a","A");
        linkedHashMap.put("b","B");
        linkedHashMap.forEach((k,v) -> {
            log.info(k + "----" + v);
        });
        LinkedHashMap<String,String> linkedHashMap1 = new LinkedHashMap<>( 10,0.75f,false);
        linkedHashMap1.put("a","A");
        linkedHashMap1.put("b","B");
        linkedHashMap1.forEach((k,v) -> {
            log.info(k + "----" + v);
        });
    }

    /**
     * 构建数据信息
     *
     * @return List<Person>
     * @author Ma_wei
     * @since 2020/4/23
     */
    private List<Person> buildData() {
        List<Person> data = Lists.newArrayList();
        for (int i = 0; i < 65537;i ++) {
            Person p = new Person();
            p.setCardId(123456789);
            p.setAge(i);
            p.setBrithday(new DateTime().toDate());
            p.setName("测试人员" + i);
            data.add(p);
        }
        return data;
    }

    /**
     * 测试easyExcel的写操作
     *
     * @author Ma_wei
     * @since 2020/4/23
     */
    @Test
    public void testEasyExcelWrite01() {
        String PATH = "D:\\Channelsoft\\workspace_idea\\channelsoft\\springcloud\\spring\\";
        String fileName = PATH + "easy-excel-write.xlsx";
        EasyExcel.write(fileName, Person.class).sheet("模板").doWrite(buildData());
    }

    /**
     * 测试easyExcel的写操作
     *
     * @author Ma_wei
     * @since 2020/4/23
     */
    @Test
    public void testEasyExcelRead01() {
        String PATH = "D:\\Channelsoft\\workspace_idea\\channelsoft\\springcloud\\spring\\";
        String fileName = PATH + "easy-excel-write.xlsx";
        EasyExcel.read(fileName, Person.class, new CellDataDemoHeadDataListener()).sheet().doRead();
    }

    @Test
    public void jodaDateTimeTest01(){
        LocalDateTime localDateTime = new LocalDateTime();
        System.out.println(localDateTime.toString("yyyy-MM-dd HH:mm:dd"));
        System.out.println("hourOfDay = " + localDateTime.getHourOfDay());
        localDateTime.getEra();
    }

    /**
     * // 当前日期时间
     *         DateTime curDateTime = new DateTime();
     *         // 当月对账日期
     *         DateTime billDate = curDateTime.withDayOfMonth(day);
     *         // 最大对账周期日期
     *         DateTime maxBillDate = billDate.plusDays(7);
     *         return billDate.isEqual(curDateTime) || maxBillDate.isEqual(curDateTime) || (curDateTime.isBefore(maxBillDate.getMillis()) && curDateTime.isAfter(billDate.getMillis()));
     *
     * @param
     * @return
     * @author Ma_wei
     * @since 2020/11/17
     */
    @Test
    public void test01(){
        int billDay = 16;
        DateTime curDateTime = new DateTime();
        int curMon = curDateTime.getMonthOfYear();
        int curYear = curDateTime.getYear();
        log.info("curYear = " + curYear + ",curMon = " + curMon);
        int lastMon = curMon - 1;
        int nextMon = curMon + 1;
        log.info("curYear = " + curYear + "lastMon = " + lastMon);
        log.info("curYear = " + curYear + "nextMon = " + nextMon);
        DateTime lastDate = curDateTime.withDate(curYear,lastMon,billDay);
        DateTime nextDate = curDateTime.withDate(curYear,nextMon,billDay);
        log.info("lastDate = " + lastDate.toString() + " nextDate = " + nextDate.toString());
    }

    @Test
    public void testSomeCase(){
        for (int i = 0; i < 10; i ++) {
            String switchFlag = "";
            if (i % 2 == 0) {
                switchFlag = "A";
            } else {
                switchFlag = "C";
            }

            switch (switchFlag) {
                case "A":
                case "B":
                    System.out.println("aaaa");
                    System.out.println("bbbb");
                    break;
                case "C":
                    System.out.println("cccc");
                    break;
                case "D":
                    System.out.println("dddd");
                    break;
                default:
                    System.out.println("未知");
            }
        }




    }
}
