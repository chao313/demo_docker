package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

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


public class TRemoteHostVo {

    private String id;  // 主机id 
    private String belongUserId;  // 主机属于用户的ID 
    private String hostIp;  // 主机IP 
    private String port;  // 主机端口号 
    private String hostUser;  // 主机端用户 
    private String passwd;  // 主机密码 
    private String remark;  // 备注 
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

    public String getHostIp() {

        return hostIp;

    }

    public void setHostIp(String hostIp) {

        this.hostIp = hostIp;

    }

    public String getPort() {

        return port;

    }

    public void setPort(String port) {

        this.port = port;

    }

    public String getHostUser() {

        return hostUser;

    }

    public void setHostUser(String hostUser) {

        this.hostUser = hostUser;

    }

    public String getPasswd() {

        return passwd;

    }

    public void setPasswd(String passwd) {

        this.passwd = passwd;

    }

    public String getRemark() {

        return remark;

    }

    public void setRemark(String remark) {

        this.remark = remark;

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
        return "TRemoteHostVo{" +
                ", id '" + id + '\'' +
                ", belongUserId '" + belongUserId + '\'' +
                ", hostIp '" + hostIp + '\'' +
                ", port '" + port + '\'' +
                ", hostUser '" + hostUser + '\'' +
                ", passwd '" + passwd + '\'' +
                ", remark '" + remark + '\'' +
                ", status '" + status +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
