package demo.spring.boot.docker.service;


import java.util.List;

import demo.spring.boot.docker.vo.TComponentCommonCmdVo;


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
public interface TComponentCommonCmdService {

    /**
     * insert
     */
    boolean insert(TComponentCommonCmdVo vo);


    /**
     * insert vos 批量插入
     */
    boolean insert(List<TComponentCommonCmdVo> vos);


    /**
     * 查询base
     */
    List<TComponentCommonCmdVo> queryBase(TComponentCommonCmdVo query);


    /**
     * update base (exclude value is null or "")
     */
    boolean updateBase(TComponentCommonCmdVo source, TComponentCommonCmdVo target);

    /**
     * update base (include value is null or "")
     */
    boolean updateBaseIncludeNull(TComponentCommonCmdVo source, TComponentCommonCmdVo target);

    /**
     * 删除base
     */
    boolean deleteBase(TComponentCommonCmdVo vo);


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
    boolean deleteByPrimaryKey(String id, Integer deleteFlag);


}
