package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/05/15
*/
@Table(name = "uac_dict")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacDict implements Serializable {
    @Id
    private Long id;

    /**
     * 组别
     */
    @Column(name = "dict_group")
    private String dictGroup;

    /**
     * code值
     */
    @Column(name = "dict_code")
    private Long dictCode;

    /**
     * 名称
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 描述
     */
    @Column(name = "dict_desp")
    private String dictDesp;

    /**
     * 排序
     */
    @Column(name = "order_by")
    private Integer orderBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

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
    @Column(name = "last_operator")
    private String lastOperator;

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
     * 版本号
     */
    private Integer version;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}