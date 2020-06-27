package cn.puc.uas.system;

import cn.puc.uas.common.utils.IdWorker;
import cn.puc.uas.common.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

//1.配置springboot的包扫描
@SpringBootApplication(scanBasePackages = "cn.puc.uas")
//2.配置jpa注解的扫描
@EntityScan(value="cn.puc.uas.domain.system")
public class SystemApplication {
    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
}
