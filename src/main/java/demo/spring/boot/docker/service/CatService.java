package demo.spring.boot.docker.service;

import demo.spring.boot.docker.vo.Cat;

public interface CatService {

    Cat queryById(Integer id);

}
