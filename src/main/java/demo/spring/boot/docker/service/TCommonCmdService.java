package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TCommonCmdVo;


/**
 * 对应的表名   :t_common_cmd
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-11
 * 表字符集    :utf8_general_ci
 * 表注释      :可执行的cmd命令
 */
public interface TCommonCmdService {

    /**
     * insert
     */
    boolean insert(TCommonCmdVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TCommonCmdVo> vos);


    /**
     * 查询base
     */
    List<TCommonCmdVo> queryBase(TCommonCmdVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TCommonCmdVo source, TCommonCmdVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TCommonCmdVo source, TCommonCmdVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TCommonCmdVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : cmd id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    TCommonCmdVo queryByPrimaryKey(String id, Integer deleteFlag);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : cmd id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    boolean deleteByPrimaryKey(String id, Integer deleteFlag);


}
