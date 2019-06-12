package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.table.TComponentPersonFileVo;

import org.apache.ibatis.annotations.Param;


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
public interface TComponentPersonFileDao {

    /**
     * insert
     */
    int insert(TComponentPersonFileVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TComponentPersonFileVo> vos);

    /**
     * 查询base
     */
    List<TComponentPersonFileVo> queryBase(TComponentPersonFileVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TComponentPersonFileVo source, @Param(value = "target") TComponentPersonFileVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TComponentPersonFileVo source, @Param(value = "target") TComponentPersonFileVo target);

    /**
     * 删除base
     */
    int deleteBase(TComponentPersonFileVo vo);



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
    int deleteByPrimaryKey(String id);

}
