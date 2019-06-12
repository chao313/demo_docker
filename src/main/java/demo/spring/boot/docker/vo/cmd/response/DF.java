package demo.spring.boot.docker.vo.cmd.response;

import demo.spring.boot.docker.constant.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录 DF 返回值的类
 */


class Info {
    private String filesystem;
    private String size;
    private String used;
    private String avail;
    private String usePer;
    private String mountedOn;

    public Info(String filesystem, String size, String used, String avail, String usePer, String mountedOn) {
        this.filesystem = filesystem;
        this.size = size;
        this.used = used;
        this.avail = avail;
        this.usePer = usePer;
        this.mountedOn = mountedOn;
    }

    public String getFilesystem() {
        return filesystem;
    }

    public void setFilesystem(String filesystem) {
        this.filesystem = filesystem;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getUsePer() {
        return usePer;
    }

    public void setUsePer(String usePer) {
        this.usePer = usePer;
    }

    public String getMountedOn() {
        return mountedOn;
    }

    public void setMountedOn(String mountedOn) {
        this.mountedOn = mountedOn;
    }
}

public class DF {

    public static String CMD_PREFIX = "df";
    public static String CMD_DF_HL = "df -hl";

    /**
     * 只过滤出 /dev开头的设备
     */
    public static String ATTACH_CMD = " | grep \"^/dev\"  | awk \'{print \"" + Constant.RESPONSE_DATA + "\" $1 \",\" $2 \",\" $3 \",\"$4\",\"$5 \",\" $6}\'";


    private List<Info> infos = new ArrayList<>();

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public void addInfo(String filesystem, String size, String used, String avail, String usePer, String mountedOn) {
        Info info = new Info(filesystem, size, used, avail, usePer, mountedOn);
        this.infos.add(info);
    }

}
