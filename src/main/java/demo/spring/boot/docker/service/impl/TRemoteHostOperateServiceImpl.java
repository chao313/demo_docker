package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TRemoteHostOperateService;
import demo.spring.boot.docker.vo.TRemoteHostOperateVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.mybatis.dao.TRemoteHostOperateDao;



/**
 * 对应的表名   :t_remote_host_operate
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :远程操作cmd返回值记录
 */
@Service
public class TRemoteHostOperateServiceImpl implements TRemoteHostOperateService {

    @Autowired
    private TRemoteHostOperateDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TRemoteHostOperateVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TRemoteHostOperateVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TRemoteHostOperateVo> queryBase(TRemoteHostOperateVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TRemoteHostOperateVo source, TRemoteHostOperateVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TRemoteHostOperateVo source, TRemoteHostOperateVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TRemoteHostOperateVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  操作id
     */
    @Override
    public TRemoteHostOperateVo queryByPrimaryKey(String id) {

        return dao.queryByPrimaryKey(id);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 操作id
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {

        return dao.deleteByPrimaryKey(id) > 0 ? true : false;

    }

}
