package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/05/22
*/
@Table(name = "work_summary")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkSummary implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 工作内容
     */
    private String content;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 分类
     */
    @Column(name = "division_id")
    private Long divisionId;

    @Column(name = "work_time")
    private String workTime;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}