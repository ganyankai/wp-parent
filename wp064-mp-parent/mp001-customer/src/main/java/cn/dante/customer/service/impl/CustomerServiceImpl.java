package cn.dante.customer.service.impl;

import cn.dante.customer.entity.Customer;
import cn.dante.customer.mapper.CustomerMapper;
import cn.dante.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
//    @Autowired
//    private BookRepository stuRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public List<Customer> getList() {

        return customerMapper.list();
    }



}
