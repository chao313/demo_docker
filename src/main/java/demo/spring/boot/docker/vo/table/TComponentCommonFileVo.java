package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

/**
 * 对应的表名   :t_component_common_file
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :通用文件组件安装
 */


public class TComponentCommonFileVo {

    private String id;  // 通用文件组件id 
    private String name;  // 安装文件的name 
    private String redhatFile;  // 文件组件在redhat上的安装 
    private String debianFile;  // 文件组件在debian上的安装 
    private String binFile;  // 文件组件二进制安装 
    private Integer status;  // 0-未启用 1-启用 
    private String location;  // 安装文件的安装location 
    private Timestamp createTime;  // 创建时间（申请时间） 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 


    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getRedhatFile() {

        return redhatFile;

    }

    public void setRedhatFile(String redhatFile) {

        this.redhatFile = redhatFile;

    }

    public String getDebianFile() {

        return debianFile;

    }

    public void setDebianFile(String debianFile) {

        this.debianFile = debianFile;

    }

    public String getBinFile() {

        return binFile;

    }

    public void setBinFile(String binFile) {

        this.binFile = binFile;

    }

    public Integer getStatus() {

        return status;

    }

    public void setStatus(Integer status) {

        this.status = status;

    }

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public Timestamp getCreateTime() {

        return createTime;

    }

    public void setCreateTime(Timestamp createTime) {

        this.createTime = createTime;

    }

    public Timestamp getUpdateTime() {

        return updateTime;

    }

    public void setUpdateTime(Timestamp updateTime) {

        this.updateTime = updateTime;

    }

    public Integer getDeleteFlag() {

        return deleteFlag;

    }

    public void setDeleteFlag(Integer deleteFlag) {

        this.deleteFlag = deleteFlag;

    }


    @Override
    public String toString() {
        return "TComponentCommonFileVo{" +
                ", id '" + id + '\'' +
                ", name '" + name + '\'' +
                ", redhatFile '" + redhatFile + '\'' +
                ", debianFile '" + debianFile + '\'' +
                ", binFile '" + binFile + '\'' +
                ", status '" + status +
                ", location '" + location + '\'' +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
