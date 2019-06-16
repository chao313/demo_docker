package demo.spring.boot.docker.config.wesocket.spring;


import java.util.List;

/**
 * 注意一一对应 相应等级的，对应相应的ID
 * 目标人群
 */
class Target {
    private String level;//消息发送的等级;all -> 发送给全部的人; department-> 等级为部门;person -> 指定个人
    private List<String> ids;//记录level对应的Id,用逗号分隔
    private String userId;//当前的发送userId

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

/**
 * 回复人群
 */
class Back {
    private String level;//消息回复的等级;all -> 全部的人的回复都会到发起人; department->  同一个部门只会回复一次 ; person -> 指定个人必须回复
    private List<String> ids;//记录level对应的已经回复的Id,用逗号分隔
    private String userId;//当前的回复userId

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


public class SocketMessageSup {

    public final static String ALL = "all";
    public final static String DEPARTMENT = "department";
    public final static String PERSON = "person";


    private String uuid;//消息的单号 -> 唯一标识

    private String code;//消息code

    private String message;

    private String date;

    private Target target; //消息的目标

    private Back back; //消息的回复

    private String originUserId; //消息的来源

    private String needBack; //消息是否需要回馈到发送人

    private String isCompleted; //消息是否终结

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Back getBack() {
        return back;
    }

    public void setBack(Back back) {
        this.back = back;
    }

    public String getOriginUserId() {
        return originUserId;
    }

    public void setOriginUserId(String originUserId) {
        this.originUserId = originUserId;
    }

    public String getNeedBack() {
        return needBack;
    }

    public void setNeedBack(String needBack) {
        this.needBack = needBack;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(String isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}