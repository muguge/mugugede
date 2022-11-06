package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *救助站信息：(RescueStationInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "RescueStationInformation")
public class RescueStationInformation implements Serializable {

    //RescueStationInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rescue_station_information_id")
    private Integer rescue_station_information_id;
   // 救助站名称
   @Basic
    private String name_of_rescue_station;
   // 救助站容量
   @Basic
    private String capacity_of_rescue_station;
   // 救助站地址
   @Basic
    private String address_of_rescue_station;
   // 负责人
   @Basic
    private String person_in_charge;
   // 联系方式
   @Basic
    private String contact_information;
   // 救助站图片
   @Basic
    private String rescue_station_picture;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
