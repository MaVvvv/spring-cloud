package com.hxb.jdk8.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import lombok.val;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * TODO
 *
 * @author Ma_wei
 * @version 1.0
 * @className FileTest1.class
 * @since 2021/5/22 15:53
 */
public class FileTest1 {

    @Test
    public void test1() throws IOException {
        //curl --header "PRIVATE-TOKEN: <your_access_token>" --remote-header-name --remote-name "https://gitlab.example.com/api/v4/projects/5/export/download"
        //String url = "http://gitlab.sunyur.com/sunyur_sop_code/api_channel_template/-/archive/channel_v0.0.1_template/api_channel_template-channel_v0.0.1_template.zip?sha=87302276";
        String url = "http://gitlab.sunyur.com/api/v4/projects/518/export/download";
        File file = new File("E:\\workspace_user\\code_gen\\api_template\\master");
        if (!file.exists()) {
            file.mkdirs();
        }
        OkHttpClient client = new OkHttpClient.Builder().build();
        Builder builder = new Builder();
        builder.addHeader("PRIVATE-TOKEN","sz3dcQBGZGDTgzy5r32h");

        val request = builder.url(url).get();
        Call call = client.newCall(request.build());
        Response execute = call.execute();
        System.out.println(execute.body().string());
        FileUtil.writeFromStream(execute.body().byteStream(),"E:\\workspace_user\\code_gen\\api_template\\master\\api_channel_template.zip");
        //FileUtil.writeFromStream(execute.body().byteStream(),file,true);
        //HttpUtil.downloadFile(url,file);
    }

}
