package cn.dante.jk.domain;

public class ContractProduct {
    private String id;

    //hibernate如此配置
//    private Contract contract;

    //mybatis如此配置
    private String contractId;      //关联关系的表,都成为普通字段

    private String factoryId;

    //冗余字段
    private String factoryName;



}
