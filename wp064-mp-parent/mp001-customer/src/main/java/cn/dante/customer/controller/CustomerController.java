package cn.dante.customer.controller;

import cn.dante.customer.entity.Customer;
import cn.dante.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping("/list")
    public List<Customer> list(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request);
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
//        response.setHeader("Access-Control-Allow-Origin","file:///D:/001_work/wp-parent/wp011-web-vue/src/main/resources/templates/03_ajax/01_firstajxa.html");

//        file:///D:/001_work/wp-parent/wp011-web-vue/src/main/resources/templates/03_ajax/01_firstajxa.html
        //这种方式竟然可以处理来自以上请求的跨域问题
        response.setHeader("Access-Control-Allow-Origin","null");

        return customerService.getList();
    }

}
