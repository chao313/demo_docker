package demo.spring.boot.docker.util.ssh;

import com.jcraft.jsch.*;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.TRemoteHostVo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShellSDK {
    private String id;
    //远程主机的ip地址
    private String ip;
    //远程主机登录用户名
    private String username;
    //远程主机的登录密码
    private String password;
    //设置ssh连接的远程端口
    private int port = 22;
    //保存输出内容的容器
    private ArrayList<String> stdout;


    private Session session;

    private ChannelExec channelExec;

    private BufferedReader input;

    public ShellSDK() {
    }

    /**
     * 初始化登录信息
     *
     * @param ip
     * @param username
     * @param password
     */
    public ShellSDK(final String ip, final String username, final String password, final int port) {
        this.id = UUIDUtils.generateUUID();
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.port = port;
        stdout = new ArrayList<String>();
    }

    public ShellSDK(TRemoteHostVo tRemoteHostVo) throws JSchException {
        this.id = UUIDUtils.generateUUID();
        this.ip = tRemoteHostVo.getHostIp();
        this.username = tRemoteHostVo.getHostUser();
        this.password = tRemoteHostVo.getPasswd();
        this.port = Integer.valueOf(tRemoteHostVo.getPort());
        stdout = new ArrayList<String>();
    }

    public ShellSDK login() throws JSchException {
        JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();
        //创建session并且打开连接，因为创建session之后要主动打开连接
        this.session = jsch.getSession(this.username, this.ip, this.port);
        this.session.setPassword(this.password);
        this.session.setUserInfo(userInfo);
        this.session.setConfig("StrictHostKeyChecking", "no");
        this.session.connect();
        return this;
    }

    /**
     * 执行命令
     *
     * @param command
     * @return
     * @throws JSchException
     * @throws IOException
     */
    public int execute(final String command) throws JSchException, IOException {
        //开启执行通道
        this.channelExec = (ChannelExec) this.session.openChannel("exec");
        int returnCode = 0;
        channelExec.setCommand(command);
        channelExec.setInputStream(null);
        this.input = new BufferedReader(new InputStreamReader
                (channelExec.getInputStream()));

        channelExec.connect();
        //接收远程服务器执行命令的结果
        String line;
        while ((line = input.readLine()) != null) {
            stdout.add(line);
        }
        input.close();

        // 得到returnCode
        if (channelExec.isClosed()) {
            returnCode = channelExec.getExitStatus();
        }
        //关闭执行通道
        this.channelExec.disconnect();
        return returnCode;
    }

    /**
     * 关闭当前连接
     */
    public void close() {
        this.session.disconnect();
    }


    /**
     * 获取shell的连接状态
     *
     * @return
     */
    public boolean isConnect() {
        return this.session.isConnected();
    }

    /**
     * get stdout
     *
     * @return
     */
    public ArrayList<String> getStandardOutput() {
        return stdout;
    }

    public String getId() {
        return id;
    }
}