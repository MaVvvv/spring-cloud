package com.mxw.hxb.springboot.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.mxw.hxb.springboot.test.constant.Param1;
import com.mxw.hxb.springboot.test.constant.TestEnum;
import com.mxw.hxb.springboot.test.jd.*;
import com.mxw.hxb.springboot.test.model.Kd100LogisticsSubscribeModel;
import lombok.val;
import okhttp3.FormBody;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-09-04 11:46
 */
public class Test {

    @org.junit.Test
    public void testListToMap(){
        List<ClassGroup> classGroupList = Lists.newArrayList();
        for (Long i = 0L; i < 10; i++) {
            ClassGroup classGroup = new ClassGroup();
            classGroup.setStudentId(i);
            classGroup.setTeacherId(i % 3);
            classGroupList.add(classGroup);
        }
        classGroupList.forEach(System.out::println);

        System.out.println("=========================================");

        Map<Long,List<Long>> group = classGroupList.stream().collect(Collectors.groupingBy(ClassGroup::getTeacherId,Collectors.mapping(ClassGroup::getStudentId,Collectors.toList())));

        System.out.println(group);
        group.forEach((key,value) -> {
            System.out.println(key + ">>>>>" + value);
        });
    }

    @org.junit.Test
    public void testDateTime(){
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toLocalDate().toString());
        DateTime billDate = dateTime.withDayOfMonth(4);
        System.out.println(billDate.toLocalDate().toString());
        DateTime day7 = billDate.plusDays(7);
        System.out.println(day7.toLocalDate().toString());

        boolean flag = dateTime.isEqualNow() || dateTime.isBefore(day7.getMillis()) && dateTime.isAfter(billDate.getMillis());
        //boolean flag = billDate.isEqualNow() || billDate.isAfterNow() || day7.isEqualNow() || day7.isBeforeNow();
        Assert.isTrue(flag);
    }

    @org.junit.Test
    public void testEnum(){
        String val = "NONE1";
        try {
            TestEnum testEnum = TestEnum.valueOf(val);

        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        System.out.println(val == TestEnum.NONE.name());
    }

    @org.junit.Test
    public void testNoPhone(){
        String phone = "17600815262";
        String no = "SF10238163012841A";
        String matchingNo = Joiner.on(":").join(no,phone.substring(phone.length() - 4));
        System.out.println(matchingNo);
        org.junit.Assert.assertEquals(matchingNo, no + ":5262");
    }

    @org.junit.Test
    public void testStrToMap(){
        String str = "parm=1123&sign=1293887jdauyhfq";
        Map<String,String> paramMap = Splitter.on("&").withKeyValueSeparator("=").split(str);
        paramMap.forEach((key,value) -> {
            System.out.println(key + " ------ " + value);
        });
        String jsonStr = JSONObject.toJSONString(paramMap);
        Param1 param1 = JSONObject.parseObject(jsonStr,Param1.class);
        System.out.println(param1);

    }

    @org.junit.Test
    public void parseMediaType(){
        String type = "application/x-www-form-urlencoded";
        String body = "{\"customer\":\"928C4E7C38D7E2048FB57976F7CAB4C0\",\"param\":{\"com\":\"yuantong\",\"num\":\"YT4846451105011\",\"phone\":\"18851856669\"},\"sign\":\"1530DCB33C4EB9011A97E6BBFF3BE117\"}";
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse(type);
        val requestBody = FormBody.create(mediaType, body);
        System.out.println(mediaType);
        System.out.println(requestBody);
    }

    @org.junit.Test
    public void encodeUrl() throws UnsupportedEncodingException {
        String oldUrl = "businessId=2019041000068593&tag=SEND_ORDER%3ARECEIVE_LOGISTICS_MESSAGE&accessToken=QUxscDdRN0REck5pNnp5OWNURE42NE5zcmluS0k2bEt6REJkVSUyRmRSNFFiMCUzREBzbTF5UGdtMkZIakJjRmNKUlgyRU45Nnl1YkZYTlIlMkJXbGg2dU5EWEh5UXJiSzRweWlPcFNzd3dYVlAzVWVFRzlAY1dvazJFNVhOTUwxTE56aHlaNkZIQSUzRCUzREAxNzY1MTIxMTQ1&clientId=XaTAGhnd5281&param=%7B%22status%22%3A%22polling%22%2C%22lastResult%22%3A%7B%22message%22%3A%22ok%22%2C%22state%22%3A%223%22%2C%22status%22%3A%22200%22%2C%22condition%22%3A%22F00%22%2C%22ischeck%22%3A%220%22%2C%22com%22%3A%22shentong%22%2C%22nu%22%3A%22773060665188774%22%2C%22data%22%3A%5B%7B%22context%22%3A%22%E4%B8%8A%E6%B5%B7%E5%88%86%E6%8B%A8%E4%B8%AD%E5%BF%83%2F%E8%A3%85%E4%BB%B6%E5%85%A5%E8%BD%A6%E6%89%AB%E6%8F%8F+%22%2C%22time%22%3A%222012-08-28+16%3A33%3A19%22%2C%22ftime%22%3A%222012-08-28+16%3A33%3A19%22%2C%22status%22%3A%22%E7%AD%BE%E6%94%B6%22%2C%22areaCode%22%3A%22310000000000%22%2C%22areaName%22%3A%22%E4%B8%8A%E6%B5%B7%E5%B8%82%22%7D%2C%7B%22context%22%3A%22%E4%B8%8A%E6%B5%B7%E5%88%86%E6%8B%A8%E4%B8%AD%E5%BF%83%2F%E4%B8%8B%E8%BD%A6%E6%89%AB%E6%8F%8F+%22%2C%22time%22%3A%222012-08-27+23%3A22%3A42%22%2C%22ftime%22%3A%222012-08-27+23%3A22%3A42%22%2C%22status%22%3A%22%E5%9C%A8%E9%80%94%22%2C%22areaCode%22%3A%22310000000000%22%2C%22areaName%22%3A%22%E4%B8%8A%E6%B5%B7%E5%B8%82%22%7D%5D%7D%2C%22message%22%3A%22ok%22%7D";
        String newUrl = URLDecoder.decode(oldUrl, Charset.defaultCharset().displayName());
        System.out.println(newUrl);
    }

    @org.junit.Test
    public void url2Map() {
        String decodeInfo = "param={\"status\":\"polling\",\"lastResult\":{\"message\":\"ok\",\"state\":\"3\",\"status\":\"200\",\"condition\":\"F00\",\"ischeck\":\"0\",\"com\":\"shentong\",\"nu\":\"773060665188774\",\"data\":[{\"context\":\"上海分拨中心/装件入车扫描 \",\"time\":\"2012-08-28 16:33:19\",\"ftime\":\"2012-08-28 16:33:19\",\"status\":\"签收\",\"areaCode\":\"310000000000\",\"areaName\":\"上海市\"},{\"context\":\"上海分拨中心/下车扫描 \",\"time\":\"2012-08-27 23:22:42\",\"ftime\":\"2012-08-27 23:22:42\",\"status\":\"在途\",\"areaCode\":\"310000000000\",\"areaName\":\"上海市\"}]},\"message\":\"ok\"}";
        // 转化为json
        Map<String,String> paramMap = Splitter.on("&").withKeyValueSeparator("=").split(decodeInfo);
        paramMap.forEach((key,value) -> {
            System.out.println(key + "-------" + value);
        });
        String paramJson = paramMap.get("param");
        String json = JSONObject.toJSONString(paramMap);
        System.out.println(json);

        String newJson = JSON.parse(json).toString();
        System.out.println(newJson);

        Kd100LogisticsSubscribeModel.Param logisticsSubscribeModel = JSONObject.parseObject(paramJson,Kd100LogisticsSubscribeModel.Param.class);
        System.out.println(logisticsSubscribeModel);
    }

    @org.junit.Test
    public void testKd100Sign(){
        String param = "{\"com\":\"shentong\",\"num\":\"773060665188774\",\"phone\":\"13011115621\"}WlzXYokI459673B7C5D9C8C7702B5EAB31EA2953A2BF";
        String result = SignUtils.sign(param);
        System.out.println(result);
    }

    @org.junit.Test
    public void testReg(){
        String str = "aaa::bbb::ccc/qqie/1.png";
        String reg = "\\[::]";
        System.out.println(str.matches(reg));
    }

    @org.junit.Test
    public void testName() {
        String old = "/privateFile/abc/1.png";
        String name = old.substring(old.lastIndexOf("/") + 1);
        System.out.println(name);
    }

    @org.junit.Test
    public void testSubImageName() {
        String old = "/privateFile/abc/1.png";
        String name = old.substring(old.indexOf("/privateFile") + "/privateFile".length() + 1);
        System.out.println(name);
    }

    @org.junit.Test
    public void testSubList(){
        List<String> list1 = Lists.newArrayList("1","3","5","7","9");
        List<String> list2 = Lists.newArrayList("1","3","5","7","8");
        Sets.SetView<String> union = Sets.union(Sets.newHashSet(list1.iterator()), Sets.newHashSet(list2.iterator()));
        union.forEach(System.out::println);
    }

    @org.junit.Test
    public void testClientStr() {
        String clientInfoStr = this.buildFindClientInfos("ceshi1");
        System.out.println(clientInfoStr);
    }


    /**
     * 构建多clientId查询like条件
     *
     * @param clientId  电商、第三方应用提供的客户编号
     * @return String   可以模糊匹配的clientInfo串
     * @author Ma_wei
     * @since 2020/10/29
     */
    private String buildFindClientInfos(String clientId){
        // [{"clientId":"11111","clientSecret":"###########"}]
        return Joiner.on(":").join("\"clientId\"","\"" + clientId + "\"");
    }

    @org.junit.Test
    public void test01(){
        int billDay = 2;
        Date billEndDate = new Date(1606888101000L);
        System.out.println(billEndDate);

        LocalDate curDateTime = LocalDate.now();
        LocalDate lastDate = curDateTime.minusMonths(1).withDayOfMonth(billDay);
        LocalDate nextDate = curDateTime.plusMonths(1).withDayOfMonth(billDay);
        System.out.println("lastDate = " + lastDate + ",nextDate = " + nextDate);
        Instant instant = billEndDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime billLocalDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate billLocalDate = billLocalDateTime.toLocalDate();

        boolean flag = billLocalDate.isAfter(lastDate) && billLocalDate.isBefore(nextDate);
        System.out.println(flag);
    }

    @org.junit.Test
    public void test02 () {
        String teg2 = "00acf0f963eda7da3847b97ccd2abdc.jpg";
        String teg1 = "c78d825743/publicImage/000acf0f963eda7da3847b97ccd2abdc.jpg";
        String teg = "c78d8257435d4900/publicImage/000acf0f963eda7da3847b97ccd2abdc.jpg";
        List<String> tegs = new ArrayList<>();
        tegs.add(teg);
        tegs.add(teg1);
        tegs.add(teg2);
        tegs.forEach(tegD -> {
            int index = tegD.indexOf("/");
            if (index == 16) {
                System.out.println(tegD);
            }
        });
    }

    @org.junit.Test
    public void testJDInvoice() {
        List<JxgInvoiceSubmitParam.InvoiceSubmitItem> invoiceSubmitItems = Lists.newArrayList();

        String markId = "";
        String invoiceOutlines = "";
        List<JdInvoiceOutlineRespModel> invoiceOutlineRespModels = JSONObject.parseArray(invoiceOutlines,JdInvoiceOutlineRespModel.class);
        if (CollectionUtils.isNotEmpty(invoiceOutlineRespModels)) {
            invoiceSubmitItems = invoiceOutlineRespModels.stream().map(invoice -> new FIJdInvoiceOutlineToInvoiceSubmit().apply(invoice)).collect(Collectors.toList());
        }

        JxgInvoiceSubmitParam jxgInvoiceSubmitParam = new JxgInvoiceSubmitParam();
        jxgInvoiceSubmitParam.setPcode(markId);
        jxgInvoiceSubmitParam.setStatus(1);
        jxgInvoiceSubmitParam.setItems(invoiceSubmitItems);

        System.out.println(JSONObject.toJSONString(jxgInvoiceSubmitParam));
    }

    @org.junit.Test
    public void testIn() {
        String detail = "{\n" +
                "    \"pcode\": \"PA201221000076\",\n" +
                "    \"count\": 8,\n" +
                "    \"totalpage\": 1,\n" +
                "    \"page\": 1,\n" +
                "    \"billCode\": \"202012010001372520\",\n" +
                "    \"type\": 1,\n" +
                "    \"vatType\": 1,\n" +
                "    \"taxAmount\": 40.52,\n" +
                "    \"nakedAmount\": 314.82,\n" +
                "    \"amount\": 355.34,\n" +
                "    \"companyName\": \"上海精准德邦货运代理有限公司\",\n" +
                "    \"companyPhone\": \"021-39285079\",\n" +
                "    \"taxpayerCode\": \"91310118MA1JNF1L0W\",\n" +
                "    \"companyAddress\": \"上海市青浦区北青公路9138号1幢8层H区817室\",\n" +
                "    \"bank\": \"中国工商银行上海徐泾支行\",\n" +
                "    \"bankAccount\": \"1001741909300070105\",\n" +
                "    \"bankAddress\": \"上海市青浦区京华路71-73号\",\n" +
                "    \"bankName\": \"中国工商银行上海徐泾支行\",\n" +
                "    \"receiverName\": \"李鑫\",\n" +
                "    \"receiverMobile\": \"15117237243\",\n" +
                "    \"receiverEmail\": \"lixin197@deppon.com\",\n" +
                "    \"createTime\": \"2020-12-21 19:31:36\",\n" +
                "    \"receiverAddressInfo\": {\n" +
                "      \"provinceCode\": \"310000000000\",\n" +
                "      \"provinceName\": \"上海\",\n" +
                "      \"cityCode\": \"310100000000\",\n" +
                "      \"cityName\": \"上海市\",\n" +
                "      \"countryCode\": \"310118000000\",\n" +
                "      \"countryName\": \"青浦区\",\n" +
                "      \"townCode\": \"310118106000\",\n" +
                "      \"townName\": \"徐泾镇\",\n" +
                "      \"address\": \"明珠路1018号德邦快递D2-4楼\"\n" +
                "    },\n" +
                "    \"remark\": null,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201115000001\",\n" +
                "        \"skuId\": \"100013872984\",\n" +
                "        \"skuName\": \"得力 deli 18205-02 新国标安全插座 插排/插线板/接线板/排插/拖线板 总控开关 儿童保护门 6组合孔2米\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 87,\n" +
                "        \"deliveryCode\": \"129120235414\",\n" +
                "        \"orderCode\": \"134256799209\",\n" +
                "        \"type\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201115000002\",\n" +
                "        \"skuId\": \"100008591942\",\n" +
                "        \"skuName\": \"保拉(Paola) 美工刀大号 18mm裁剪刀 裁纸刀 墙纸刀2002\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 112,\n" +
                "        \"deliveryCode\": \"136089436390\",\n" +
                "        \"orderCode\": \"134253230049\",\n" +
                "        \"type\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201116000004\",\n" +
                "        \"skuId\": \"100010374756\",\n" +
                "        \"skuName\": \"得力（deli） 插座 插排/插线板/接线板/排插/拖线板 新国标 总控开关 18255\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 78,\n" +
                "        \"deliveryCode\": \"129127524056\",\n" +
                "        \"orderCode\": \"134256799209\",\n" +
                "        \"type\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201129000015\",\n" +
                "        \"skuId\": \"2129072\",\n" +
                "        \"skuName\": \"运费\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 2,\n" +
                "        \"deliveryCode\": \"133120775731\",\n" +
                "        \"orderCode\": \"133120775731\",\n" +
                "        \"type\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201129000016\",\n" +
                "        \"skuId\": \"2129072\",\n" +
                "        \"skuName\": \"运费\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 4,\n" +
                "        \"deliveryCode\": \"133120775731\",\n" +
                "        \"orderCode\": \"133120775731\",\n" +
                "        \"type\": 2\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201129000017\",\n" +
                "        \"skuId\": \"385768\",\n" +
                "        \"skuName\": \"得力(deli)φ70mm圆形塑壳财务会计专用秒干印台印泥 红色\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 3.64,\n" +
                "        \"deliveryCode\": \"133188817333\",\n" +
                "        \"orderCode\": \"133120775731\",\n" +
                "        \"type\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201129000018\",\n" +
                "        \"skuId\": \"277374\",\n" +
                "        \"skuName\": \"得力(deli)36g高粘度PVA固体胶水/胶棒 12支/盒 办公用品 7103\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 30.9,\n" +
                "        \"deliveryCode\": \"133188817333\",\n" +
                "        \"orderCode\": \"133120775731\",\n" +
                "        \"type\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"pcode\": \"ARAP201129000019\",\n" +
                "        \"skuId\": \"2129072\",\n" +
                "        \"skuName\": \"SAHOO触屏秋冬季骑行手套全指自行车手套长指男女山地车手套单车装备 XL码\",\n" +
                "        \"skuSpec\": null,\n" +
                "        \"amount\": 37.8,\n" +
                "        \"deliveryCode\": \"133194715224\",\n" +
                "        \"orderCode\": \"133120775731\",\n" +
                "        \"type\": 1\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        InvoiceDetailModel invoiceDetailModel = JSONObject.parseObject(detail,InvoiceDetailModel.class);
        List<InvoiceDetailItemsModel> items = invoiceDetailModel.getItems();
        String orderCodes = com.google.api.client.repackaged.com.google.common.base.Joiner.on(",").join(
                items.stream()
                        .filter(itemsModel -> itemsModel.getType().equals(1))
                        .map(InvoiceDetailItemsModel::getDeliveryCode)
                        .distinct()
                        .collect(Collectors.toList())
        );
        System.out.println(orderCodes);
    }

    @org.junit.Test
    public void testClassName () {
        String className = "com.mxw.hxb.springboot.service.impl.MyResultServiceImpl";
        String packageName = className.substring(0,className.lastIndexOf("."));
        System.out.println(packageName);
    }
}
