package demo.spring.boot.docker.vo.cmd.response;

import demo.spring.boot.docker.constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录 ps 返回值的类
 */


class ProcessInfo {
    private String user;//进程所属者
    private String pid;//进程id
    private String cpuPer;//cpu占用率
    private String memPer;//内存占用率
    private String vsz;//进程占用的虚拟内存容量
    private String rss;//该进程占用的固定內存量（KB）（驻留中页的数量）
    private String tty;//进程在哪个中断执行，如果与终端无关就?;若为pts/0等，则表示由网络连接主机进程
    private String stat;//狀態位
    private String start;//进程启动时间
    private String time;//进程启动起来使用cpu的总和数据
    private String command;//启动进程的命令名称

    public ProcessInfo(String user, String pid, String cpuPer, String memPer, String vsz, String rss, String tty, String stat, String start, String time, String command) {
        this.user = user;
        this.pid = pid;
        this.cpuPer = cpuPer;
        this.memPer = memPer;
        this.vsz = vsz;
        this.rss = rss;
        this.tty = tty;
        this.stat = stat;
        this.start = start;
        this.time = time;
        this.command = command;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCpuPer() {
        return cpuPer;
    }

    public void setCpuPer(String cpuPer) {
        this.cpuPer = cpuPer;
    }

    public String getMemPer() {
        return memPer;
    }

    public void setMemPer(String memPer) {
        this.memPer = memPer;
    }

    public String getVsz() {
        return vsz;
    }

    public void setVsz(String vsz) {
        this.vsz = vsz;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getTty() {
        return tty;
    }

    public void setTty(String tty) {
        this.tty = tty;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}

public class PS {

    public static String CMD_PREFIX = "ps";
    public static String CMD = "ps aux | grep -v PID ";
    //    4 -> Mem
    //    3 -> CPU
    public static String ORBER_CMD = " | sort -rn -k +";
    public static String HEAD = " | head -n ";

    public static String AWK = " | awk \'{print \"" + Constant.RESPONSE_DATA + "\" $1 \",\" $2 \",\" $3 \",\"$4\",\"$5 \",\" $6 \",\" $7 \",\" $8 \",\" $9 \",\" $10 \",\" $11 \",\" $12}\'";


    private List<ProcessInfo> processInfos = new ArrayList<>();

    public List<ProcessInfo> getProcessInfos() {
        return processInfos;
    }

    public void setProcessInfos(List<ProcessInfo> processInfos) {
        this.processInfos = processInfos;
    }

    public void addProcessInfo(String user, String pid, String cpuPer, String memPer, String vsz, String rss, String tty, String stat, String start, String time, String command) {
        ProcessInfo processInfo = new ProcessInfo(user, pid, cpuPer, memPer, vsz, rss, tty, stat, start, time, command);
        this.processInfos.add(processInfo);
    }
}
