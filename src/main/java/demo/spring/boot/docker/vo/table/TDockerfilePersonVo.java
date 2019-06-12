package demo.spring.boot.docker.vo.table;

import java.sql.Timestamp;

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


public class TDockerfilePersonVo {

    private String id;  // 个人dockerfile id 
    private String belongUserId;  // 属于的用户ID 
    private String name;  // dockerfile 的name 
    private String dockerfileBaseImage;  // docker的基础image 
    private String dockerfile;  // docker的内容 
    private String dockerfileTargertName;  // dcokerFile生成目标镜像的名称 
    private byte[] dockerfileGit;  // dcokerFile的.git文件 
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

    public String getDockerfileBaseImage() {

        return dockerfileBaseImage;

    }

    public void setDockerfileBaseImage(String dockerfileBaseImage) {

        this.dockerfileBaseImage = dockerfileBaseImage;

    }

    public String getDockerfile() {

        return dockerfile;

    }

    public void setDockerfile(String dockerfile) {

        this.dockerfile = dockerfile;

    }

    public String getDockerfileTargertName() {

        return dockerfileTargertName;

    }

    public void setDockerfileTargertName(String dockerfileTargertName) {

        this.dockerfileTargertName = dockerfileTargertName;

    }

    public byte[] getDockerfileGit() {

        return dockerfileGit;

    }

    public void setDockerfileGit(byte[] dockerfileGit) {

        this.dockerfileGit = dockerfileGit;

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
        return "TDockerfilePersonVo{" +
                ", id '" + id + '\'' +
                ", belongUserId '" + belongUserId + '\'' +
                ", name '" + name + '\'' +
                ", dockerfileBaseImage '" + dockerfileBaseImage + '\'' +
                ", dockerfile '" + dockerfile + '\'' +
                ", dockerfileTargertName '" + dockerfileTargertName + '\'' +
                ", dockerfileGit '" + dockerfileGit +
                ", status '" + status +
                ", createTime '" + createTime +
                ", updateTime '" + updateTime +
                ", deleteFlag '" + deleteFlag +
                '}';
    }

}
