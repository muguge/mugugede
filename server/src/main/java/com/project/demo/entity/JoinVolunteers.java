package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *加入志愿者：(JoinVolunteers)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "JoinVolunteers")
public class JoinVolunteers implements Serializable {

    //JoinVolunteers编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "join_volunteers_id")
    private Integer join_volunteers_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 姓名
   @Basic
    private String full_name;
   // 联系方式
   @Basic
    private String contact_information;
   // 备注
   @Basic
    private String remarks;
   // 申请详情
   @Basic
    private String application_details_;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
