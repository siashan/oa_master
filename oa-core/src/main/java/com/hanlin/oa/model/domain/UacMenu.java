package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/05/17
*/
@Table(name = "uac_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacMenu implements Serializable {
    @Id
    private Long id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单类型
     */
    @Column(name = "menu_type")
    private Integer menuType;

    /**
     * 菜单url
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
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序字段
     */
    @Column(name = "order_by")
    private Integer orderBy;

    /**
     * 状态  0 禁用  1 可用
     */
    private Integer status;

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
    @Column(name = "redirect_path")
    private String redirectPath;

    /**
     * 是否隐藏
     */
    @Column(name = "is_hide")
    private Integer isHide;

    /**
     * 是否最后一级
     */
    @Column(name = "is_leaf")
    private Integer isLeaf;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建人id
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 最后操作人
     */
    @Column(name = "last_opertor")
    private String lastOpertor;

    /**
     * 最后操作人id
     */
    @Column(name = "last_operator_id")
    private Long lastOperatorId;

    /**
     * 最后更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 版本控制
     */
    private Integer version;

    @Transient
    private ArrayList<UacMenu> children;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}