package com.cloudtimes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 启动程序
 *
 * @author tank
 */
@EnableWebMvc
@MapperScan("com.cloudtimes.*")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DetectionServerApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication application = new SpringApplication(DetectionServerApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
        System.out.println("云时代检测服务启动成功");
    }
}
