package demo.spring.boot.docker.util.ssh.impl;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.util.ssh.ShellSDKInterface;
import demo.spring.boot.docker.util.ssh.other.MyUserInfo;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class Login {

    List<String> stdout;
    Session session;
    ChannelExec channelExec;
    BufferedReader input;

    public Login(String ip, String username, String password, int port) throws JSchException {
        JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();
        //创建session并且打开连接，因为创建session之后要主动打开连接
        this.session = jsch.getSession(username, ip, port);
        this.session.setPassword(password);
        this.session.setUserInfo(userInfo);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.connect();
        this.channelExec = (ChannelExec) this.session.openChannel("exec");
    }

    public List<String> exec(String cmd) throws JSchException, IOException {
        //开启执行通道
//        this.channelExec = (ChannelExec) this.session.openChannel("exec");
        channelExec.setCommand(cmd);
        channelExec.setInputStream(null);
        this.input = new BufferedReader(new InputStreamReader
                (channelExec.getInputStream()));
        channelExec.connect();
        //接收远程服务器执行命令的结果
        List<String> response = new ArrayList<>();
        String line;
        while ((line = input.readLine()) != null) {
            response.add(line);
        }
        return response;
    }


}


public class ShellSDKImpl implements ShellSDKInterface {

    private String id;
    //远程主机的ip地址
    private String ip;
    //远程主机登录用户名
    private String username;
    //远程主机的登录密码
    private String password;
    //设置ssh连接的远程端口
    private int port = 22;
    private Login login;

    @Override
    public ShellSDKInterface login(String ip, String username, String password, int port) {
        this.id = UUIDUtils.generateUUID();
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.port = port;
        try {
            this.login = new Login(ip, username, password, port);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public List<String> executeSync(String command) {
        try {
            return this.login.exec(command);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int executeAsync(String command) {
        return 0;
    }

    @Override
    public void close() {
        if (null != login) {
            if (null != login.session) {
                login.session.disconnect();
            }
        }
    }

    @Override
    public boolean isConnect() {
        if (null != login) {
            if (null != login.session) {
                return login.session.isConnected();
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getStandardOutput() {
        return null;
    }
}
