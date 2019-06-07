package demo.spring.boot.docker.mybatis.dao;

import demo.spring.boot.docker.vo.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CatDao {

    Cat queryById(@Param(value = "id") Integer id);
}
