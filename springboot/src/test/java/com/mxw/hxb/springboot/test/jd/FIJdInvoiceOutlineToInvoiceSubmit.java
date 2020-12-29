package com.mxw.hxb.springboot.test.jd;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.function.Function;

/**
 * 京东发票概要转换聚贤阁发票确认数据模型Function
 *
 * @author Ma_wei
 * @since 2020/8/7 17:49
 */
@Component
public class FIJdInvoiceOutlineToInvoiceSubmit implements Function<JdInvoiceOutlineRespModel, JxgInvoiceSubmitParam.InvoiceSubmitItem> {

    /**
     * 时间格式化
     */
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JxgInvoiceSubmitParam.InvoiceSubmitItem apply(JdInvoiceOutlineRespModel jdInvoiceOutlineRespModel) {
        JxgInvoiceSubmitParam.InvoiceSubmitItem invoiceSubmitItem = new JxgInvoiceSubmitParam.InvoiceSubmitItem();

        invoiceSubmitItem.setAmount(jdInvoiceOutlineRespModel.getInvoiceAmount());
        invoiceSubmitItem.setTaxInvoiceCode(jdInvoiceOutlineRespModel.getInvoiceId() == null ? null : jdInvoiceOutlineRespModel.getInvoiceId().trim());
        invoiceSubmitItem.setNakedAmount(jdInvoiceOutlineRespModel.getInvoiceNakedAmount());
        invoiceSubmitItem.setInvoiceNumber(jdInvoiceOutlineRespModel.getInvoiceCode() == null ? null : jdInvoiceOutlineRespModel.getInvoiceCode().trim());
        invoiceSubmitItem.setInvoiceDate(simpleDateFormat.format(jdInvoiceOutlineRespModel.getInvoiceDate()));

        BigDecimal taxRate = jdInvoiceOutlineRespModel.getInvoiceTaxRate();
        invoiceSubmitItem.setTaxRate(taxRate == null ? null : taxRate.multiply(BigDecimal.valueOf(100)));
        invoiceSubmitItem.setType(1);
        invoiceSubmitItem.setUrl(StringUtils.EMPTY);
        invoiceSubmitItem.setVatType(jdInvoiceOutlineRespModel.getInvoiceType() - 1);
        return invoiceSubmitItem;
    }
}
