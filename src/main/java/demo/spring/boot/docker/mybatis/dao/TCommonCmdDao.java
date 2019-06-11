package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.TCommonCmdVo;

import org.apache.ibatis.annotations.Param;


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
public interface TCommonCmdDao {

    /**
     * insert
     */
    int insert(TCommonCmdVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TCommonCmdVo> vos);

    /**
     * 查询base
     */
    List<TCommonCmdVo> queryBase(TCommonCmdVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TCommonCmdVo source, @Param(value = "target") TCommonCmdVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TCommonCmdVo source, @Param(value = "target") TCommonCmdVo target);

    /**
     * 删除base
     */
    int deleteBase(TCommonCmdVo vo);


    /**
     * 根据PrimaryKey查询
     * <p>
     * id : cmd id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    TCommonCmdVo queryByPrimaryKey(@Param(value = "id") String id, @Param(value = "deleteFlag") Integer deleteFlag);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : cmd id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    int deleteByPrimaryKey(@Param(value = "id") String id, @Param(value = "deleteFlag") Integer deleteFlag);

}
