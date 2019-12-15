package cn.dante.customer.service;

import cn.dante.customer.entity.Customer;

import java.util.List;

//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "stu")
public interface CustomerService {
    public List<Customer> getList();

//    @GetMapping("/list")  //
//    String getStuList();

}
