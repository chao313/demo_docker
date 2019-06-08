package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.TLogVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_log
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :用户的操作日志表
 */
public interface TLogDao {

    /**
     * insert
     */
    int insert(TLogVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TLogVo> vos);

    /**
     * 查询base
     */
    List<TLogVo> queryBase(TLogVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TLogVo source, @Param(value = "target") TLogVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TLogVo source, @Param(value = "target") TLogVo target);

    /**
     * 删除base
     */
    int deleteBase(TLogVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 
     */
    TLogVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 
     */
    int deleteByPrimaryKey(String id);

}
