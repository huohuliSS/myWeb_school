package com.qhw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement  // 开启事务支持
public class MywebProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MywebProjectApplication.class, args);
    }

}
