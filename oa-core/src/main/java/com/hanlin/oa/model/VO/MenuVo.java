package com.hanlin.oa.model.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Author: kfc
 * @Description: <br/>
 * Date:Create in 2019/5/16 18:47
 * @Modified By:
 */
@Data
public class MenuVo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String menuName;

    /**
     * 类型
     */
    private Byte menuType;

    /**
     * url
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 权限
     */
    private String permission;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 备注
     */
    private String remark;


    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 重定向地址
     */
    private String redirectPath;

    /**
     * 是否隐藏
     */
    private Byte isHide;

    /**
     * 是否最后一级
     */
    private Byte isLeaf;

    private ArrayList<MenuVo> children;


    public int hashcode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {  // 重写equals()方法。
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof MenuVo) {
            MenuVo p = (MenuVo) obj;
            return id.longValue() == p.getId().longValue();
        }
        return false;
    }
}
