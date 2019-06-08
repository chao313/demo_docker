package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TComponentCommonFileVo;


/**
 * 对应的表名   :t_component_common_file
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :通用文件组件安装
 */
public interface TComponentCommonFileService {

    /**
     * insert
     */
    boolean insert(TComponentCommonFileVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TComponentCommonFileVo> vos);


    /**
     * 查询base
     */
    List<TComponentCommonFileVo> queryBase(TComponentCommonFileVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TComponentCommonFileVo source, TComponentCommonFileVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TComponentCommonFileVo source, TComponentCommonFileVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TComponentCommonFileVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 通用文件组件id
     */
    TComponentCommonFileVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 通用文件组件id
     */
    boolean deleteByPrimaryKey(String id);


}
