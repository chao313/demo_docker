package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TDockerfilePersonService;
import demo.spring.boot.docker.vo.TDockerfilePersonVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.mybatis.dao.TDockerfilePersonDao;



/**
 * 对应的表名   :t_dockerfile_person
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :个人的dockerfile的文件
 */
@Service
public class TDockerfilePersonServiceImpl implements TDockerfilePersonService {

    @Autowired
    private TDockerfilePersonDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TDockerfilePersonVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TDockerfilePersonVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TDockerfilePersonVo> queryBase(TDockerfilePersonVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TDockerfilePersonVo source, TDockerfilePersonVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TDockerfilePersonVo source, TDockerfilePersonVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TDockerfilePersonVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  个人dockerfile id<p>
     * deleteFlag  删除标志: 0未删除，1已删除
     */
    @Override
    public TDockerfilePersonVo queryByPrimaryKey(String id, Integer deleteFlag) {

        return dao.queryByPrimaryKey(id, deleteFlag);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人dockerfile id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    @Override
    public boolean deleteByPrimaryKey(String id, Integer deleteFlag) {

        return dao.deleteByPrimaryKey(id, deleteFlag) > 0 ? true : false;

    }

}
