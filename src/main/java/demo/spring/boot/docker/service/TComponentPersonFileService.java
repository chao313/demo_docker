package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TComponentPersonFileVo;


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
public interface TComponentPersonFileService {

    /**
     * insert
     */
    boolean insert(TComponentPersonFileVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TComponentPersonFileVo> vos);


    /**
     * 查询base
     */
    List<TComponentPersonFileVo> queryBase(TComponentPersonFileVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TComponentPersonFileVo source, TComponentPersonFileVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TComponentPersonFileVo source, TComponentPersonFileVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TComponentPersonFileVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 个人文件组件id
     */
    TComponentPersonFileVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人文件组件id
     */
    boolean deleteByPrimaryKey(String id);


}
