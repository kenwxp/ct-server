package com.cloudtimes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author tank
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApiApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ApiApplication.class, args);
        System.out.println("云时代系统App-Api服务启动成功");
    }
}
