package com.mxw.hxb.springboot.web;

import com.mxw.hxb.springboot.annotations.SyI18nApi;
import com.mxw.hxb.springboot.model.ApplicationClassifyModel;
import com.sunyur.common.pagination.ModelListResult;
import com.sunyur.common.pagination.ModelListResultUtil;
import com.sunyur.common.pagination.ModelResult;
import com.sunyur.common.pagination.ModelResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 国际化注解测试接口控制器
 *
 * @author Ma_wei
 * @since 2020/12/23 19:14
 */
@Slf4j
@RestController
@RequestMapping(value = "/sy-i18n")
public class SyI18nAnnoController {

    @SyI18nApi
    @GetMapping(value = "/anno")
    public ModelResult<ApplicationClassifyModel> doHandAnno() {
        ApplicationClassifyModel applicationClassifyModel = new ApplicationClassifyModel();
        applicationClassifyModel.setId(10);
        applicationClassifyModel.setApplicationClassifyName("场景");

        ApplicationClassifyModel.MenuModel menuModel = new ApplicationClassifyModel.MenuModel();
        menuModel.setId(100);
        menuModel.setMenuName("菜单");
        List<ApplicationClassifyModel.MenuModel.ApplicationModel> applicationModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ApplicationClassifyModel.MenuModel.ApplicationModel itemModel = new ApplicationClassifyModel.MenuModel.ApplicationModel();
            itemModel.setId(1000 + i);
            itemModel.setApplication("应用" + i);
            applicationModels.add(itemModel);
        }
        menuModel.setApplicationModels(applicationModels);

        List<ApplicationClassifyModel.MenuModel> menuModels = new ArrayList<>();
        menuModels.add(menuModel);
        applicationClassifyModel.setMenuModels(menuModels);

        return ModelResultUtil.success(applicationClassifyModel);
    }

    @SyI18nApi
    @GetMapping(value = "/anno/list")
    public ModelListResult<ApplicationClassifyModel> doHandAnnoList() {
        List<ApplicationClassifyModel> applicationClassifyModelList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            ApplicationClassifyModel applicationClassifyModel = new ApplicationClassifyModel();
            applicationClassifyModel.setId(10 + i);
            applicationClassifyModel.setApplicationClassifyName("场景" + i);

            List<ApplicationClassifyModel.MenuModel> menuModels = new ArrayList<>();
            for (int i1 = 0; i1 < 3; i1++) {
                ApplicationClassifyModel.MenuModel menuModel = new ApplicationClassifyModel.MenuModel();
                menuModel.setId((100 + i1) * (i + 1));
                menuModel.setMenuName("菜单" + i1);

                List<ApplicationClassifyModel.MenuModel.ApplicationModel> applicationModels = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    ApplicationClassifyModel.MenuModel.ApplicationModel applicationModel = new ApplicationClassifyModel.MenuModel.ApplicationModel();
                    applicationModel.setId((1000 + j) * (i + 1));
                    applicationModel.setApplication("应用" + j);
                    applicationModels.add(applicationModel);
                }
                menuModel.setApplicationModels(applicationModels);
                menuModels.add(menuModel);
            }
            applicationClassifyModel.setMenuModels(menuModels);
            applicationClassifyModelList.add(applicationClassifyModel);
        }
        return ModelListResultUtil.success(applicationClassifyModelList);
    }
}

