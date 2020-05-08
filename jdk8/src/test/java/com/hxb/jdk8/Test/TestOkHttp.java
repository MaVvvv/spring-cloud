package com.hxb.jdk8.Test;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-25 10:54
 */
public class TestOkHttp {

    private static final Logger log = LoggerFactory.getLogger(TestOkHttp.class);

    @Test
    public void simpleOkHttpGet() {
        log.info("-------------");
        String url = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=17600815262";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                log.debug("response:{}",response.body().string());
            }
        });
    }
}
