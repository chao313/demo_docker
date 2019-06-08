package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TUserVo;


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
public interface TUserService {

    /**
     * insert
     */
    boolean insert(TUserVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TUserVo> vos);


    /**
     * 查询base
     */
    List<TUserVo> queryBase(TUserVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TUserVo source, TUserVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TUserVo source, TUserVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TUserVo vo);


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
    boolean deleteByPrimaryKey(String id);


}
