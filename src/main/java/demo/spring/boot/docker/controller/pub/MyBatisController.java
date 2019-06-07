package demo.spring.boot.docker.controller.pub;

import demo.spring.boot.docker.mybatis.service.CatService;
import demo.spring.boot.docker.mybatis.vo.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBatisController {

    @Autowired
    private CatService catService;

    @GetMapping(value = "/mybatisquery/{id}")
    public Cat queryById(@PathVariable(value = "id") Integer id) {

        return catService.queryById(id);

    }
}
