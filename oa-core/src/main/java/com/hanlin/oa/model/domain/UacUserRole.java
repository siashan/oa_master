package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
* Created by Mybatis Generator on 2019/05/15
*/
@Table(name = "uac_user_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacUserRole implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}