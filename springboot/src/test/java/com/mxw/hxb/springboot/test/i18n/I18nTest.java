package com.mxw.hxb.springboot.test.i18n;

import com.sun.javafx.binding.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 国际化测试
 *
 * @author Ma_wei
 * @since 2020/12/23 16:07
 */
@Slf4j
public class I18nTest {

    @Test
    public void test01() {
        Locale cnLocale = Locale.CHINA;
        Locale enLocale = Locale.ENGLISH;
        String info = "中国12二AaB！";
        MessageFormat msgFcn = new MessageFormat(info,cnLocale);
        MessageFormat msgFen = new MessageFormat(info,enLocale);

        log.info("msgFcn = {}",msgFcn.toString());
        log.info("msgFen = {}",msgFen.toString());
    }
}
