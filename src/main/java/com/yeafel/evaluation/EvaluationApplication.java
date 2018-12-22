package com.yeafel.evaluation;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.yeafel.evaluation.dataobject.mapper")
@EnableCaching
public class EvaluationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }
}
