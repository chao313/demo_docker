package demo.spring.boot.docker.vo.cmd.response;

import demo.spring.boot.docker.constant.Constant;

class Mem {
    private String total;
    private String used;
    private String free;
    private String shared;
    private String buff_cache;
    private String available;

    public Mem(String total, String used, String free, String shared, String buff_cache, String available) {
        this.total = total;
        this.used = used;
        this.free = free;
        this.shared = shared;
        this.buff_cache = buff_cache;
        this.available = available;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getBuff_cache() {
        return buff_cache;
    }

    public void setBuff_cache(String buff_cache) {
        this.buff_cache = buff_cache;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}

class Swap {
    private String total;
    private String used;
    private String free;

    public Swap(String total, String used, String free) {
        this.total = total;
        this.used = used;
        this.free = free;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }
}

/**
 * 记录Free返回值的类
 */
public class Free {

    public static String CMD_PREFIX = "free";
    public static String CMD_FREE = "free";
    public static String CMD_FREE_H = "free -h";
    public static String CMD_FREE_G = "free -g";

    /**
     * free -h | awk '{if(NR==2){print "Total: " $2 " Used: " $3 "free :" $4  "share:" $5  "  buffers: "$6 " available: "  $7 }}'
     * Total: 1.8G Used: 789Mfree :265Mshare:11M  buffers: 784M available: 866M
     */
    public static String CMD_FREE_MEM = " | awk \'{if(NR==2){print \"" + Constant.RESPONSE_DATA + "\" $2 \",\" $3 \",\"$4\",\"$5\",\"$6\",\"$7 }}\'";
    public static String CMD_FREE_SWAP = " | awk \'{if(NR==3){print \"" + Constant.RESPONSE_DATA + "\" $2 \",\" $3 \",\"$4\",\"$5\",\"$6\",\"$7 }}\'";


    /**
     * 内存
     */
    private Mem mem;
    /**
     * 交换区
     */
    private Swap swap;

    public void setNewMem(String total, String used, String free, String shared, String buff_cache, String available) {
        this.mem = new Mem(total, used, free, shared, buff_cache, available);
    }

    public void setNewSwap(String total, String used, String free) {
        this.swap = new Swap(total, used, free);
    }

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }

    public Swap getSwap() {
        return swap;
    }

    public void setSwap(Swap swap) {
        this.swap = swap;
    }
}
