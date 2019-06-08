package demo.spring.boot.docker.mybatis.dao;


import java.util.List;

import demo.spring.boot.docker.vo.TDockerfilePersonVo;

import org.apache.ibatis.annotations.Param;


/**
 * 对应的表名   :t_dockerfile_person
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :个人的dockerfile的文件
 */
public interface TDockerfilePersonDao {

    /**
     * insert
     */
    int insert(TDockerfilePersonVo vo);

    /**
     * insert vos 批量插入
     */
    int inserts(@Param(value = "vos") List<TDockerfilePersonVo> vos);

    /**
     * 查询base
     */
    List<TDockerfilePersonVo> queryBase(TDockerfilePersonVo query);

    /**
     * update base (exclude value is null or "")
     */
    int updateBase(@Param(value = "source") TDockerfilePersonVo source, @Param(value = "target") TDockerfilePersonVo target);


    /**
     * update base (include value is null or "")
     */
    int updateBaseIncludeNull(@Param(value = "source") TDockerfilePersonVo source, @Param(value = "target") TDockerfilePersonVo target);

    /**
     * 删除base
     */
    int deleteBase(TDockerfilePersonVo vo);



    /**
     * 根据PrimaryKey查询
     * <p>
     * id : 个人dockerfile id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    TDockerfilePersonVo queryByPrimaryKey(String id, Integer deleteFlag);

    /**
     * 根据PrimaryKey删除
     * <p>
     * id : 个人dockerfile id<p>
     * deleteFlag : 删除标志: 0未删除，1已删除
     */
    int deleteByPrimaryKey(String id, Integer deleteFlag);

}
