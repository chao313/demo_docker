package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TComponentPersonCmdVo;


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
public interface TComponentPersonCmdService {

    /**
     * insert
     */
    boolean insert(TComponentPersonCmdVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TComponentPersonCmdVo> vos);


    /**
     * 查询base
     */
    List<TComponentPersonCmdVo> queryBase(TComponentPersonCmdVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TComponentPersonCmdVo source, TComponentPersonCmdVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TComponentPersonCmdVo source, TComponentPersonCmdVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TComponentPersonCmdVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 个人cmd命令id
     */
    TComponentPersonCmdVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人cmd命令id
     */
    boolean deleteByPrimaryKey(String id);


}
