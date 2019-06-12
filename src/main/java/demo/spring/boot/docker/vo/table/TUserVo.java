package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

/**
 * 对应的表名   :t_user
 * 表类型      :BASE TABLE
 * 表引擎      :InnoDB
 * 表版本      :10
 * 行格式      :Dynamic
 * 表创建时间   :2019-6-8
 * 表字符集    :utf8_general_ci
 * 表注释      :用户表
 */


public class TUserVo {

    private String id;  // 用户登录ID 
    private String name;  // 用户姓名 
    private String password;  // 密码 
    private String salt;  // 盐 
    private String phone;  // 手机号码 
    private String email;  // 邮箱 
    private Integer status;  // 0-未启用 1-启用 
    private Timestamp createTime;  // 创建时间（申请时间） 
    private Timestamp updateTime; 
    private Integer deleteFlag;  // 删除标志: 0未删除，1已删除 
    private Integer approveStatus;  // 0: 待审批 1：通过 ：2 拒绝 
    private Boolean whiteListUser; 


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

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

    public String getSalt() {

        return salt;

    }

    public void setSalt(String salt) {

        this.salt = salt;

    }

    public String getPhone() {

        return phone;

    }

    public void setPhone(String phone) {

        this.phone = phone;

    }

    public String getEmail() {

        return email;

    }

    public void setEmail(String email) {

        this.email = email;

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

    public Integer getApproveStatus() {

        return approveStatus;

    }

    public void setApproveStatus(Integer approveStatus) {

        this.approveStatus = approveStatus;

    }

    public Boolean getWhiteListUser() {

        return whiteListUser;

    }

    public void setWhiteListUser(Boolean whiteListUser) {

        this.whiteListUser = whiteListUser;

    }


    @Override
    public String toString() {
        return "TUserVo{" +
                ", id '" + id + '\'' +
                ", name '" + name + '\'' +
                ", password '" + password + '\'' +
                ", salt '" + salt + '\'' +
                ", phone '" + phone + '\'' +
                ", email '" + email + '\'' +
                ", status '" + status +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                ", approveStatus '" + approveStatus +
                ", whiteListUser '" + whiteListUser +
                '}';
    }

}
