package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *流浪动物消息：(StrayAnimalNews)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "StrayAnimalNews")
public class StrayAnimalNews implements Serializable {

    //StrayAnimalNews编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stray_animal_news_id")
    private Integer stray_animal_news_id;
   // 动物名称
   @Basic
    private String animal_name;
   // 动物图片
   @Basic
    private String animal_pictures;
   // 领养状态
   @Basic
    private String adoption_status;
   // 动物性别
   @Basic
    private String animal_sex;
   // 动物年龄
   @Basic
    private String age_animal;
   // 所属救助站
   @Basic
    private String affiliated_rescue_station;
   // 物种
   @Basic
    private String species;
   // 品种
   @Basic
    private String varieties;
   // 是否接种
   @Basic
    private String vaccination;
   // 是否绝育
   @Basic
    private String sterilization;
   // 是否驱虫
   @Basic
    private String is_it_insect_repellent;
   // 领养备注
   @Basic
    private String adoption_notes;
   // 详细信息
   @Basic
    private String detailed_information;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
