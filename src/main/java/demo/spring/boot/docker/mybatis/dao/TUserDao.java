package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.table.TUserVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_user
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :用户表
 */
public interface TUserDao {

    /**
     * insert
     */
    int insert(TUserVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TUserVo> vos);

    /**
     * 查询base
     */
    List<TUserVo> queryBase(TUserVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TUserVo source, @Param(value = "target") TUserVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TUserVo source, @Param(value = "target") TUserVo target);

    /**
     * 删除base
     */
    int deleteBase(TUserVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 用户登录ID
     */
    TUserVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 用户登录ID
     */
    int deleteByPrimaryKey(String id);

}
