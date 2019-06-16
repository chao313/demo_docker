package demo.spring.boot.docker.vo.cmd.response;

import demo.spring.boot.docker.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录 ps 返回值的类
 */


class FileInfo {
    private String type;//d:目录;l:软连接;b:块设备;c:字符设备;s:socket;p:管道;-:普通文件
    private String ownPer;//拥有者权限
    private String groupPer;//所属用户组权限
    private String otherPer;//其他用户权限
    private String node; //连接占用的节点
    private String fileOwner;//文件拥有者
    private String fileGroup;//文件属于组
    private String size;//文件大小
    private String lastModifyTime;//最后修改时间
    private String name;//文件名称

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnPer() {
        return ownPer;
    }

    public void setOwnPer(String ownPer) {
        this.ownPer = ownPer;
    }

    public String getGroupPer() {
        return groupPer;
    }

    public void setGroupPer(String groupPer) {
        this.groupPer = groupPer;
    }

    public String getOtherPer() {
        return otherPer;
    }

    public void setOtherPer(String otherPer) {
        this.otherPer = otherPer;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(String fileGroup) {
        this.fileGroup = fileGroup;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileInfo(String type, String ownPer, String groupPer, String otherPer, String node, String fileOwner, String fileGroup, String size, String lastModifyTime, String name) {
        this.type = type;
        this.ownPer = ownPer;
        this.groupPer = groupPer;
        this.otherPer = otherPer;
        this.node = node;
        this.fileOwner = fileOwner;
        this.fileGroup = fileGroup;
        this.size = size;
        this.lastModifyTime = lastModifyTime;
        this.name = name;
    }
}

public class LS {

    public static String CMD_PREFIX = "ls";

    public static String CMD = "ls -alh --time-style '+%Y-%m-%d %H:%M:%S' ";

    public static String END = "| grep -v ^total"; //去除第一行

    public static String AWK = " | awk \'{print \"" + Constant.RESPONSE_DATA + "\" $1 \",\" $2 \",\" $3 \",\"$4\",\"$5 \",\" $6 \",\" $7 \",\" $8 \",\" $9 \",\" $10 \",\" $11 \",\" $12}\'";


    public static String generateCMD(String path) {
        return LS.CMD + path + LS.END + LS.AWK;
    }

    private List<FileInfo> fileInfos = new ArrayList<>();

    public List<FileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(List<FileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public void addFileInfo(String type, String ownPer, String groupPer, String otherPer, String node, String fileOwner, String fileGroup, String size, String lastModifyTime, String name) {
        FileInfo fileInfo = new FileInfo(type, ownPer, groupPer, otherPer, node, fileOwner, fileGroup, size, lastModifyTime, name);
        this.fileInfos.add(fileInfo);
    }
}
