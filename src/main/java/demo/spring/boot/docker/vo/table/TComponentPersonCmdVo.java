package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

/**
 * 对应的表名   :t_component_person_cmd
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :个人cmd组件安装命令
 */


public class TComponentPersonCmdVo {

    private String id;  // 个人cmd命令id 
    private String belongUserId;  // 主机属于用户的ID 
    private String name;  // cmd组件的name 
    private String redhatCmd;  // cmd组件在redhat上的安装 
    private String debianCmd;  // cmd组件在debian上的安装 
    private String binCmd;  // cmd组件二进制安装 
    private Integer status;  // 0-未启用 1-启用 
    private Timestamp createTime;  // 创建时间（申请时间） 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 


    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getBelongUserId() {

        return belongUserId;

    }

    public void setBelongUserId(String belongUserId) {

        this.belongUserId = belongUserId;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getRedhatCmd() {

        return redhatCmd;

    }

    public void setRedhatCmd(String redhatCmd) {

        this.redhatCmd = redhatCmd;

    }

    public String getDebianCmd() {

        return debianCmd;

    }

    public void setDebianCmd(String debianCmd) {

        this.debianCmd = debianCmd;

    }

    public String getBinCmd() {

        return binCmd;

    }

    public void setBinCmd(String binCmd) {

        this.binCmd = binCmd;

    }

    public Integer getStatus() {

        return status;

    }

    public void setStatus(Integer status) {

        this.status = status;

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
        return "TComponentPersonCmdVo{" +
                ", id '" + id + '\'' +
                ", belongUserId '" + belongUserId + '\'' +
                ", name '" + name + '\'' +
                ", redhatCmd '" + redhatCmd + '\'' +
                ", debianCmd '" + debianCmd + '\'' +
                ", binCmd '" + binCmd + '\'' +
                ", status '" + status +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
