package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *领养信息：(AdoptionInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AdoptionInformation")
public class AdoptionInformation implements Serializable {

    //AdoptionInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_information_id")
    private Integer adoption_information_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 领养人姓名
   @Basic
    private String name_of_adopter;
   // 联系方式
   @Basic
    private String contact_information;
   // 动物名称
   @Basic
    private String animal_name;
   // 领养备注
   @Basic
    private String adoption_notes;
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
