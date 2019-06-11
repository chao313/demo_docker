package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TCommonCmdService;
import demo.spring.boot.docker.mybatis.dao.TCommonCmdDao;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.vo.TCommonCmdVo;


/**
 * 对应的表名   :t_common_cmd
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-11
 * 表字符集    :utf8_general_ci
 * 表注释      :可执行的cmd命令
 */
@Service
public class TCommonCmdServiceImpl implements TCommonCmdService {

    @Autowired
    private TCommonCmdDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TCommonCmdVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TCommonCmdVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TCommonCmdVo> queryBase(TCommonCmdVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TCommonCmdVo source, TCommonCmdVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TCommonCmdVo source, TCommonCmdVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TCommonCmdVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  cmd id<p>
     * deleteFlag  删除标志: 0未删除，1已删除
     */
    @Override
    public TCommonCmdVo queryByPrimaryKey(String id, Integer deleteFlag) {

        return dao.queryByPrimaryKey(id, deleteFlag);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : cmd id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    @Override
    public boolean deleteByPrimaryKey(String id, Integer deleteFlag) {

        return dao.deleteByPrimaryKey(id, deleteFlag) > 0 ? true : false;

    }

}
