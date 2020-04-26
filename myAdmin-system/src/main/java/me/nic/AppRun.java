package me.nic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目入口
 * @author nicahead@gmail.com
 * @date 2020/4/26 10:33
 */
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement(proxyTargetClass = true)
public class AppRun {
    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
        System.out.println("==========================\n" +
                "后台管理系统接口启动\n" +
                "==========================\n");
    }
}
