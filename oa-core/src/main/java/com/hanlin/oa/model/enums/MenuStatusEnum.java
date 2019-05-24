package com.hanlin.oa.model.enums;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/19 16:34
 * @Modified By:
 */
public enum MenuStatusEnum {

    ENABLE(1, "可用"),
    DISABLE(0, "禁用");

    int code;
    String value;

    MenuStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
