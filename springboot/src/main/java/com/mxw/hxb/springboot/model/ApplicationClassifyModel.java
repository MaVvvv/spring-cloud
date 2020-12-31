package com.mxw.hxb.springboot.model;

import com.mxw.hxb.springboot.annotations.SyI18nField;
import com.mxw.hxb.springboot.annotations.SyI18nFieldRoot;
import com.sunyur.common.i18n.enums.SyI18nConfigTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 场景数据模型
 *
 * @author Ma_wei
 * @since 2020/12/24 11:07
 */
@Data
public class ApplicationClassifyModel {

    private Integer id;

    @SyI18nField(type = SyI18nConfigTypeEnum.APPLICATION_CLASSIFY)
    private String applicationClassifyName;

    @SyI18nFieldRoot
    private List<MenuModel> menuModels;

    /**
     * 菜单数据模型
     *
     * @author Ma_wei
     * @since 2020/12/24 11:07
     */
    @Data
    public static class MenuModel {

        private Integer id;

        @SyI18nField(type = SyI18nConfigTypeEnum.MENU)
        private String menuName;

        @SyI18nFieldRoot
        private List<ApplicationModel> applicationModels;

        /**
         * 应用数据模型
         *
         * @author Ma_wei
         * @since 2020/12/24 11:07
         */
        @Data
        public static class ApplicationModel {

            private Integer id;

            @SyI18nField(type = SyI18nConfigTypeEnum.APPLICATION)
            private String application;
        }
    }
}
