package com.mxw.hxb.springboot.po.sopdb;

import lombok.Data;

import java.util.List;

/**
 * 1
 *
 * @author Ma_wei
 * @since 2020/11/25 14:02
 */
@Data
public class SopServiceAuthClientPO {

    private long id;

    private String clientInfos;

    @Data
    public static class ServiceAuthClientInfo {

        private String clientId;

        private String clientSecret;

        private String userName;

        private String password;

        private List<String> companyCodes;
    }
}
