package demo.spring.boot.docker.vo;

import java.sql.Timestamp;

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


public class TRemoteHostOperateVo {

    private String id;  // 操作id 
    private String belongRemoteId;  // 当前操作记录属于远程主机的ID 
    private String operateUserId;  // 当前操作人ID 
    private String operateCmd;  // 操作cmd 
    private String operateResponse;  // 操作的返回值 
    private Timestamp createTime;  // 创建时间（申请时间） 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 


    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getBelongRemoteId() {

        return belongRemoteId;

    }

    public void setBelongRemoteId(String belongRemoteId) {

        this.belongRemoteId = belongRemoteId;

    }

    public String getOperateUserId() {

        return operateUserId;

    }

    public void setOperateUserId(String operateUserId) {

        this.operateUserId = operateUserId;

    }

    public String getOperateCmd() {

        return operateCmd;

    }

    public void setOperateCmd(String operateCmd) {

        this.operateCmd = operateCmd;

    }

    public String getOperateResponse() {

        return operateResponse;

    }

    public void setOperateResponse(String operateResponse) {

        this.operateResponse = operateResponse;

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
        return "TRemoteHostOperateVo{" +
                ", id '" + id + '\'' +
                ", belongRemoteId '" + belongRemoteId + '\'' +
                ", operateUserId '" + operateUserId + '\'' +
                ", operateCmd '" + operateCmd + '\'' +
                ", operateResponse '" + operateResponse + '\'' +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
