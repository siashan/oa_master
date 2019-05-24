package com.hanlin.oa.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
* Created by Mybatis Generator on 2019/05/20
*/
@Table(name = "project_division")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDivision implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 项目id
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 分工
     */
    private String division;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}