package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.TComponentCommonFileVo;

import org.apache.ibatis.annotations.Param;


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
public interface TComponentCommonFileDao {

    /**
     * insert
     */
    int insert(TComponentCommonFileVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TComponentCommonFileVo> vos);

    /**
     * 查询base
     */
    List<TComponentCommonFileVo> queryBase(TComponentCommonFileVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TComponentCommonFileVo source, @Param(value = "target") TComponentCommonFileVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TComponentCommonFileVo source, @Param(value = "target") TComponentCommonFileVo target);

    /**
     * 删除base
     */
    int deleteBase(TComponentCommonFileVo vo);



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
    int deleteByPrimaryKey(String id);

}
