package cn.dante.customer.entity;

import lombok.Data;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
//import java.math.BigDecimal;

/**
 * @Description:	StuService接口
 * @Author:			传智播客 java学院
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2019-6-17 17:17:05
 */
//@Entity
@Data
public class Customer extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1936L;

	private Integer id;
	private String name;
	private String sex;
	private Integer birthday;
	private Double sponsorMoney;

	private Double shopMoney;
	private Integer shopType;
	private Integer score;
	private Integer blackWhite;	//int comment '0 黑名单     1 普通	2  白名单',
	private Integer vipLevel;	//0-9

	private Integer grade;
	private Date createTime;
	private Integer createBy;
	private String createDept;
	private Integer orderNo;




}
