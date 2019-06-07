package demo.spring.boot.docker.mybatis.service;

import demo.spring.boot.docker.mybatis.dao.CatDao;
import demo.spring.boot.docker.mybatis.vo.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatService {
    @Autowired
    private CatDao catDao;

    public Cat queryById(Integer id) {
        return catDao.queryById(id);
    }


}
