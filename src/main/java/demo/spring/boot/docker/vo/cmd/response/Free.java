package demo.spring.boot.docker.vo.cmd.response;

class Mem {
    private String total;
    private String used;
    private String free;
    private String shared;
    private String buff_cache;
    private String available;

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

    private static String CMD_PREFIX = "free";

    /**
     * 内存
     */
    private Mem mem;
    /**
     * 交换区
     */
    private Swap swap;

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
