package com.mxw.hxb.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.api.client.util.Lists;
import com.mxw.hxb.springboot.dao.sopdb.SopServiceAuthMapper;
import com.mxw.hxb.springboot.dao.sunyurdb.PurchaserCompanyMapper;
import com.mxw.hxb.springboot.po.sopdb.SopServiceAuthClientPO;
import com.mxw.hxb.springboot.po.sopdb.SopServiceAuthPO;
import com.mxw.hxb.springboot.po.sunyurdb.PurchaserCompanyPO;
import com.mxw.hxb.springboot.service.SyncServiceAuthInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 同步服务授权业务逻辑接口实现类
 *
 * @author Ma_wei
 * @since 2020/11/25 13:51
 */
@Slf4j
@Service
public class SyncServiceAuthInfoServiceImpl implements SyncServiceAuthInfoService {

    @Autowired
    private SopServiceAuthMapper sopServiceAuthMapper;

    @Autowired
    private PurchaserCompanyMapper purchaserCompanyMapper;

    @Value("${spring.profiles.active}")
    private String profilesActive;

    @Override
    public void sync() {
        List<SopServiceAuthPO> sopServiceAuthLists = sopServiceAuthMapper.getSopServiceAuthLists();

        File scriptFile = new File("E:\\opt\\springboot\\script","update-" + profilesActive + ".sql");
        if (!scriptFile.getParentFile().exists()) {
            scriptFile.mkdirs();
        }

        sopServiceAuthLists.forEach(sopServiceAuthPO -> {
            log.info(sopServiceAuthPO.toString());
            List<String> companyCodes = Lists.newArrayList();
            if ("mall".equals(sopServiceAuthPO.getCategory())) {
                // 获取公司主体列表
                List<PurchaserCompanyPO> purchaserCompanyPOS = purchaserCompanyMapper.getPurchaserCompanyLists(sopServiceAuthPO.getPurchaserId());
                companyCodes = purchaserCompanyPOS.stream().map(PurchaserCompanyPO::getCode).collect(Collectors.toList());
            }
            SopServiceAuthClientPO.ServiceAuthClientInfo authClientInfo = new SopServiceAuthClientPO.ServiceAuthClientInfo();
            authClientInfo.setClientId(sopServiceAuthPO.getClientId());
            authClientInfo.setClientSecret(sopServiceAuthPO.getClientSecret());
            authClientInfo.setUserName(sopServiceAuthPO.getUserName());
            authClientInfo.setPassword(sopServiceAuthPO.getPassword());
            authClientInfo.setCompanyCodes(companyCodes);

            SopServiceAuthClientPO po = new SopServiceAuthClientPO();
            po.setId(sopServiceAuthPO.getId());
            po.setClientInfos(JSONObject.toJSONString(authClientInfo));

            String sql = "update sop_service_auth set client_infos = '[" + JSONObject.toJSONString(authClientInfo) + "]' where id = " + sopServiceAuthPO.getId() + ";\n";
            log.info(sql);

            try {
                org.apache.commons.io.FileUtils.writeStringToFile(scriptFile,sql,"utf-8",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //sopServiceAuthMapper.updateSopServiceAuthClientInfo(po);
        });
    }
}
