package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *志愿者用户：(VolunteerUsers)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VolunteerUsers")
public class VolunteerUsers implements Serializable {

    //VolunteerUsers编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_users_id")
    private Integer volunteer_users_id;
   // 志愿者用户名
   @Basic
    private String volunteer_user_name;
   // 志愿者编号
   @Basic
    private String volunteer_number;
   // 姓名
   @Basic
    private String full_name;
   // 性别
   @Basic
    private String gender;
   // 年龄
   @Basic
    private String age;
   // 身份证
   @Basic
    private String id;
   // 地址
   @Basic
    private String address;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
