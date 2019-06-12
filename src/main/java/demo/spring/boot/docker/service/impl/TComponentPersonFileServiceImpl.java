package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TComponentPersonFileService;
import demo.spring.boot.docker.vo.table.TComponentPersonFileVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.mybatis.dao.TComponentPersonFileDao;



/**
 * 对应的表名   :t_component_person_file
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :个人文件组件安装
 */
@Service
public class TComponentPersonFileServiceImpl implements TComponentPersonFileService {

    @Autowired
    private TComponentPersonFileDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TComponentPersonFileVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TComponentPersonFileVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TComponentPersonFileVo> queryBase(TComponentPersonFileVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TComponentPersonFileVo source, TComponentPersonFileVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TComponentPersonFileVo source, TComponentPersonFileVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TComponentPersonFileVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  个人文件组件id
     */
    @Override
    public TComponentPersonFileVo queryByPrimaryKey(String id) {

        return dao.queryByPrimaryKey(id);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人文件组件id
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {

        return dao.deleteByPrimaryKey(id) > 0 ? true : false;

    }

}
