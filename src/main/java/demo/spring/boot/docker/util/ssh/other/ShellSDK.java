package demo.spring.boot.docker.util.ssh.other;

import com.jcraft.jsch.*;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.TRemoteHostVo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    private ChannelShell channelShell;

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
        stdout = new ArrayList<>();
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
        this.channelExec = (ChannelExec) this.session.openChannel("exec");
        this.channelShell = (ChannelShell) session.openChannel("shell");
        channelShell.connect(3000);
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
//        this.channelExec = (ChannelExec) this.session.openChannel("exec");
        int returnCode = 0;
        channelExec.setCommand(command);
        channelExec.setInputStream(null);
        this.input = new BufferedReader(new InputStreamReader
                (channelExec.getInputStream()));

//        channelExec.connect();
        //接收远程服务器执行命令的结果
        String line;
        while ((line = input.readLine()) != null) {
            stdout.add(line);
        }
//        input.close();

        // 得到returnCode
        if (channelExec.isClosed()) {
            returnCode = channelExec.getExitStatus();
        }
        //关闭执行通道
//        this.channelExec.disconnect();
        return returnCode;
    }

    public List<String> exec2(String cmd) {
        List<String> response = new ArrayList<>();
        try {
            InputStream input = channelShell.getInputStream();
            OutputStream output = channelShell.getOutputStream();
            output.write((cmd + " \n\r").getBytes());
            output.flush();
            TimeUnit.SECONDS.sleep(1);
            byte[] tmp = new byte[1024];
            int end = input.available();
            while (end > 0) {
                int i = input.read(tmp, 0, 1024);
                end = input.available();
                if (i < 0)
                    break;
                response.add(new String(tmp, 0, i));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return response;

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