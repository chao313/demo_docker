package demo.spring.boot.docker.config.wesocket.javax.vo;


class Data {
    private String hostName;
    private int port;
    private String username;
    private String secret;

    public String getHostName() {
        return hostName;
    }


    public String getUsername() {
        return username;
    }

    public String getSecret() {
        return secret;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

public class Term2Vo {
    public static String INIT = "init";
    public static String DATA = "hostName";

    private String tp; //init
    private Data data;
    private String message;

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getDataHost() {
        return data.getHostName();
    }

    public int getDataPort() {
        return data.getPort();
    }

    public String getDataUsername() {
        return data.getUsername();
    }

    public String getDataSecret() {
        return data.getSecret();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
