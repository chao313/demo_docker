package demo.spring.boot.docker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "demo.spring.boot.docker.mybatis.dao")//mybatis
public class DemoDockerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDockerApplication.class, args);
    }

}
