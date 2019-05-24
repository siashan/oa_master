package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/05/19
*/
@Table(name = "uac_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacRole implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态 0 禁用 1可用
     */
    private Byte status;

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
    @Column(name = "last_operarot")
    private String lastOperarot;

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

    /**
     * 备注
     */
    private String remark;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}