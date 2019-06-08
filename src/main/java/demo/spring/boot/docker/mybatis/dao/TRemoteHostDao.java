package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.TRemoteHostVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_remote_host
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :远程主机
 */
public interface TRemoteHostDao {

    /**
     * insert
     */
    int insert(TRemoteHostVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TRemoteHostVo> vos);

    /**
     * 查询base
     */
    List<TRemoteHostVo> queryBase(TRemoteHostVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TRemoteHostVo source, @Param(value = "target") TRemoteHostVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TRemoteHostVo source, @Param(value = "target") TRemoteHostVo target);

    /**
     * 删除base
     */
    int deleteBase(TRemoteHostVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 主机id
     */
    TRemoteHostVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 主机id
     */
    int deleteByPrimaryKey(String id);

}
