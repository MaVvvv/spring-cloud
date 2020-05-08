package com.hxb.jdk8.Test;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-11-25 11:12
 */
public class TestOkHttp1 {

    private static final Logger log = LoggerFactory.getLogger(TestOkHttp1.class);

    public static void main(String[] args) {
        log.info("-------------");
        String url = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=17600815261";
        /*OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                log.debug(response.body().string());
            }
        });*/

        //Request request = new Request.Builder().url(url).header("Client-Language", Locale.getDefault().getLanguage()).header("123");
    }
}
