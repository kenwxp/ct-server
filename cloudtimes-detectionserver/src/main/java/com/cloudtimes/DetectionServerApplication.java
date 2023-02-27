package com.cloudtimes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author tank
 */
@MapperScan("com.cloudtimes.**.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DetectionServerApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(DetectionServerApplication.class, args);
        System.out.println("云时代检测服务启动成功");
    }
}
