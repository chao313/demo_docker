package demo.spring.boot.docker.service.impl;


import java.util.List;

import demo.spring.boot.docker.service.TComponentPersonCmdService;
import demo.spring.boot.docker.vo.table.TComponentPersonCmdVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import demo.spring.boot.docker.mybatis.dao.TComponentPersonCmdDao;



/**
 * 对应的表名   :t_component_person_cmd
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :个人cmd组件安装命令
 */
@Service
public class TComponentPersonCmdServiceImpl implements TComponentPersonCmdService {

    @Autowired
    private TComponentPersonCmdDao dao;

    /**
     * insert
     */
    @Override
    public boolean insert(TComponentPersonCmdVo vo) {

        return dao.insert(vo) > 0 ? true : false;

    }

    /**
     * insert vos 批量插入
     */
    @Override
    public boolean insert(List<TComponentPersonCmdVo> vos) {

        return dao.inserts(vos) > 0 ? true : false;

    }

    /**
     * 查询base
     */
    @Override
    public List<TComponentPersonCmdVo> queryBase(TComponentPersonCmdVo query) {

        return dao.queryBase(query);

    }

    /**
     * update base (exclude value is null or "")
     */
    @Override
    public boolean updateBase(TComponentPersonCmdVo source, TComponentPersonCmdVo target) {

        return dao.updateBase(source, target) > 0 ? true : false;

    }

    /**
     * update base (include value is null or "")
     */
    @Override
    public boolean updateBaseIncludeNull(TComponentPersonCmdVo source, TComponentPersonCmdVo target) {

        return dao.updateBaseIncludeNull(source, target) > 0 ? true : false;

    }

    /**
     * 删除base
     */
    @Override
    public boolean deleteBase(TComponentPersonCmdVo vo) {

        return dao.deleteBase(vo) > 0 ? true : false;

    }


    /**
     * 根据PrimaryKey查询
     * <p>
     * id  个人cmd命令id
     */
    @Override
    public TComponentPersonCmdVo queryByPrimaryKey(String id) {

        return dao.queryByPrimaryKey(id);

    }

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人cmd命令id
     */
    @Override
    public boolean deleteByPrimaryKey(String id) {

        return dao.deleteByPrimaryKey(id) > 0 ? true : false;

    }

}
