package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.table.TRemoteHostOperateVo;


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
public interface TRemoteHostOperateService {

    /**
     * insert
     */
    boolean insert(TRemoteHostOperateVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TRemoteHostOperateVo> vos);


    /**
     * 查询base
     */
    List<TRemoteHostOperateVo> queryBase(TRemoteHostOperateVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TRemoteHostOperateVo source, TRemoteHostOperateVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TRemoteHostOperateVo source, TRemoteHostOperateVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TRemoteHostOperateVo vo);


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
    boolean deleteByPrimaryKey(String id);


}
