package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

/**
 * 对应的表名   :t_log
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :用户的操作日志表
 */


public class TLogVo {

    private String id; 
    private String userId;  // 用户登录ID 
    private String userName;  // 用户姓名 
    private String eventType;  // 事件类型 
    private String eventSource;  // 触发事件源id 
    private String content;  // 事件内容 
    private Timestamp createTime;  // 创建时间 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 


    public String getId() {

        return id;

    }

    public void setId(String id) {

        this.id = id;

    }

    public String getUserId() {

        return userId;

    }

    public void setUserId(String userId) {

        this.userId = userId;

    }

    public String getUserName() {

        return userName;

    }

    public void setUserName(String userName) {

        this.userName = userName;

    }

    public String getEventType() {

        return eventType;

    }

    public void setEventType(String eventType) {

        this.eventType = eventType;

    }

    public String getEventSource() {

        return eventSource;

    }

    public void setEventSource(String eventSource) {

        this.eventSource = eventSource;

    }

    public String getContent() {

        return content;

    }

    public void setContent(String content) {

        this.content = content;

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
        return "TLogVo{" +
                ", id '" + id + '\'' +
                ", userId '" + userId + '\'' +
                ", userName '" + userName + '\'' +
                ", eventType '" + eventType + '\'' +
                ", eventSource '" + eventSource + '\'' +
                ", content '" + content + '\'' +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
