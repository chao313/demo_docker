package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.vo.TUserVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.mybatis.dao.TUserDao;



/**
 * 对应的表名   :t_user
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :用户表
 */
@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TUserVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TUserVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TUserVo> queryBase(TUserVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TUserVo source, TUserVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TUserVo source, TUserVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TUserVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  用户登录ID
     */
    @Override
    public TUserVo queryByPrimaryKey(String id) {

        return dao.queryByPrimaryKey(id);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 用户登录ID
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {

        return dao.deleteByPrimaryKey(id) > 0 ? true : false;

    }

}
