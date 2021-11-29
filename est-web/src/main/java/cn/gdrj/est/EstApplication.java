package cn.gdrj.est;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 默认的扫描范围： 启动类所在的包及子包
@SpringBootApplication
@MapperScan({"cn.gdrj.est.dao"})
public class EstApplication {
    public static void main(String[] args) {
        SpringApplication.run(EstApplication.class,args);
    }
}
