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
@Table(name = "uac_role_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacRoleMenu implements Serializable {
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
     * 菜单id
     */
    @Column(name = "menu_id")
    private Long menuId;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}