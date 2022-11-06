package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *爱心募捐：(CharityFundraising)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CharityFundraising")
public class CharityFundraising implements Serializable {

    //CharityFundraising编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charity_fundraising_id")
    private Integer charity_fundraising_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 姓名
   @Basic
    private String full_name;
   // 募捐类型
   @Basic
    private String fundraising_type;
   // 物品名称
   @Basic
    private String item_name;
   // 捐款数额
   @Basic
    private String amount_of_contributions;
   // 备注
   @Basic
    private String remarks;
   // 联系方式
   @Basic
    private String contact_information;
   // 物品详情
   @Basic
    private String item_details;
    // 审核状态
    @Basic
    private String examine_state;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;
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
