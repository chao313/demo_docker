package demo.spring.boot.docker.vo;

import java.sql.Timestamp;

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


public class TCommonCmdVo {

    private String id;  // cmd id 
    private String name;  // 命令的名字 
    private String remark;  // 解释 
    private String cmd;  // cmd组件在redhat上的执行命令 
    private String dependent;  // cmd需要依赖的命令 
    private Timestamp createTime;  // 创建时间（申请时间） 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 
    private String operatingSystem;  // 操作系统类型 
    private String upperVersion;  // 系统版本的上限 
    private String lowerVersion;  // 系统版本的下限 


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

    public String getRemark() {

        return remark;

    }

    public void setRemark(String remark) {

        this.remark = remark;

    }

    public String getCmd() {

        return cmd;

    }

    public void setCmd(String cmd) {

        this.cmd = cmd;

    }

    public String getDependent() {

        return dependent;

    }

    public void setDependent(String dependent) {

        this.dependent = dependent;

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

    public String getOperatingSystem() {

        return operatingSystem;

    }

    public void setOperatingSystem(String operatingSystem) {

        this.operatingSystem = operatingSystem;

    }

    public String getUpperVersion() {

        return upperVersion;

    }

    public void setUpperVersion(String upperVersion) {

        this.upperVersion = upperVersion;

    }

    public String getLowerVersion() {

        return lowerVersion;

    }

    public void setLowerVersion(String lowerVersion) {

        this.lowerVersion = lowerVersion;

    }


    @Override
    public String toString() {
        return "TCommonCmdVo{" +
                ", id '" + id + '\'' +
                ", name '" + name + '\'' +
                ", remark '" + remark + '\'' +
                ", cmd '" + cmd + '\'' +
                ", dependent '" + dependent + '\'' +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                ", operatingSystem '" + operatingSystem + '\'' +
                ", upperVersion '" + upperVersion + '\'' +
                ", lowerVersion '" + lowerVersion + '\'' +
                '}';
    }

}
