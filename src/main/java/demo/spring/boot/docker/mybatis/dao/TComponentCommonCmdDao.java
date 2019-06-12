package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.table.TComponentCommonCmdVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_component_common_cmd
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :通用的cmd组件安装命令
 */
public interface TComponentCommonCmdDao {

    /**
     * insert
     */
    int insert(TComponentCommonCmdVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TComponentCommonCmdVo> vos);

    /**
     * 查询base
     */
    List<TComponentCommonCmdVo> queryBase(TComponentCommonCmdVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TComponentCommonCmdVo source, @Param(value = "target") TComponentCommonCmdVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TComponentCommonCmdVo source, @Param(value = "target") TComponentCommonCmdVo target);

    /**
     * 删除base
     */
    int deleteBase(TComponentCommonCmdVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 通用cmd组件id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    TComponentCommonCmdVo queryByPrimaryKey(String id, Integer deleteFlag);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 通用cmd组件id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    int deleteByPrimaryKey(String id, Integer deleteFlag);

}
