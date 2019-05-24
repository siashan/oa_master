package com.hanlin.oa.model.enums;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/4/24 14:55
 * @Modified By:
 */
public enum MenuTypeEnum {
    MENU_TYPE_0(0,"根目录"),
    MENU_TYPE_1(1,"一级菜单"),
    MENU_TYPE_2(2,"二级菜单"),
    MENU_TYPE_3(3,"三级菜单"),
    MENU_TYPE_4(4,"按钮"),
    MENU_TYPE_5(5,"超链接"),
    MENU_TYPE_6(6,"非折叠菜单");

    int type;
    String value;
    MenuTypeEnum(int type,String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
