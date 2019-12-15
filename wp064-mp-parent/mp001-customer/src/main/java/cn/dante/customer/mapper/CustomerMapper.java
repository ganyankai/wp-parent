package cn.dante.customer.mapper;

import cn.dante.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper  //可包扫描也可以单个配置,两个同时使用好像也可以
public interface CustomerMapper {
    public List<Customer> list();


}
