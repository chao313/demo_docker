package demo.spring.boot.docker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = "demo.spring.boot.demospringboot.mybatis.dao")//mybatis
@EnableCaching//表示redis开启缓存
public class DemoDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDockerApplication.class, args);
    }

}
