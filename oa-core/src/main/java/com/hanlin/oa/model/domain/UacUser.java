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
@Table(name = "uac_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UacUser implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 性别
     */
    private String gender;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 联系电话
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建日期
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最近登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * openID
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 首次登陆是否修改过密码 0 没有 1 已修改
     */
    @Column(name = "is_password_changed")
    private String isPasswordChanged;

    /**
     * 创建人id
     */
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 创建人姓名
     */
    private String creator;

    /**
     * 最后登录ip
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录位置
     */
    @Column(name = "last_login_loc")
    private String lastLoginLoc;

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
     * 版本控制
     */
    private Integer version;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}