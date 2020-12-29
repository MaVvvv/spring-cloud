package com.mxw.hxb.springboot.po.sopdb;

import lombok.Data;

/**
 * 服务授权数据持久化实例
 *
 * @author Ma_wei
 * @since 2020/11/25 13:59
 */
@Data
public class SopServiceAuthPO {

    private Long id;

    private Long serviceId;

    private Long purchaserId;

    private String clientId;

    private String category;

    private String clientSecret;

    private String userName;

    private String password;

}
