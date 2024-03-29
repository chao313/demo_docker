package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.table.TRemoteHostOperateVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_remote_host_operate
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :远程操作cmd返回值记录
 */
public interface TRemoteHostOperateDao {

    /**
     * insert
     */
    int insert(TRemoteHostOperateVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TRemoteHostOperateVo> vos);

    /**
     * 查询base
     */
    List<TRemoteHostOperateVo> queryBase(TRemoteHostOperateVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TRemoteHostOperateVo source, @Param(value = "target") TRemoteHostOperateVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TRemoteHostOperateVo source, @Param(value = "target") TRemoteHostOperateVo target);

    /**
     * 删除base
     */
    int deleteBase(TRemoteHostOperateVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 操作id
     */
    TRemoteHostOperateVo queryByPrimaryKey(String id);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 操作id
     */
    int deleteByPrimaryKey(String id);

}
