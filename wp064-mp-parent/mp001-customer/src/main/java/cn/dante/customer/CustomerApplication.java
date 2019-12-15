package cn.dante.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//		(exclude=
//		{DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//@EnableEurekaClient

public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

}
