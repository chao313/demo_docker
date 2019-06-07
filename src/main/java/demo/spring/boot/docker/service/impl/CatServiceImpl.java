package demo.spring.boot.docker.service.impl;

import demo.spring.boot.docker.mybatis.dao.CatDao;
import demo.spring.boot.docker.service.CatService;
import demo.spring.boot.docker.vo.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatDao catDao;

    public Cat queryById(Integer id) {
        return catDao.queryById(id);
    }


}
