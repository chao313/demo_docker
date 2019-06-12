package demo.spring.boot.docker.util.ssh.other;

import com.jcraft.jsch.*;
import demo.spring.boot.docker.vo.table.TRemoteHostVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Shell {
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

    public Shell() {
    }

    /**
     * 初始化登录信息
     *
     * @param ip
     * @param username
     * @param password
     */
    public Shell(final String ip, final String username, final String password, final int port) {
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.port = port;
        stdout = new ArrayList<String>();
    }

    public Shell initByTRemoteHostVo(TRemoteHostVo tRemoteHostVo) {
        this.ip = tRemoteHostVo.getHostIp();
        this.username = tRemoteHostVo.getHostUser();
        this.password = tRemoteHostVo.getPasswd();
        this.port = Integer.valueOf(tRemoteHostVo.getPort());
        stdout = new ArrayList<String>();
        return this;
    }

    /**
     * 执行shell命令
     *
     * @param command
     * @return
     */
    public int execute(final String command) throws JSchException {
        int returnCode = 0;
        JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();

        try {
            //创建session并且打开连接，因为创建session之后要主动打开连接
            Session session = jsch.getSession(username, ip, port);
            session.setPassword(password);
            session.setUserInfo(userInfo);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            //打开通道，设置通道类型，和执行的命令
            Channel channel = session.openChannel("exec");
            ChannelExec channelExec = (ChannelExec) channel;
            channelExec.setCommand(command);

            channelExec.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader
                    (channelExec.getInputStream()));

            channelExec.connect();
            System.out.println("The remote command is :" + command);

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

            // 关闭通道
            channelExec.disconnect();
            //关闭session
            session.disconnect();

        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnCode;
    }

    /**
     * get stdout
     *
     * @return
     */
    public ArrayList<String> getStandardOutput() {
        return stdout;
    }


    /**
     * 测试是否能够连接到主机
     *
     * @return
     * @throws JSchException
     */
    public boolean testConnect() throws JSchException {
        //创建session并且打开连接，因为创建session之后要主动打开连接
        JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();
        Session session = jsch.getSession(this.username, this.ip, this.port);
        session.setPassword(this.password);
        session.setUserInfo(userInfo);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        return true;
    }


    public static void main(final String[] args) throws JSchException {
        String ip = "39.107.236.187";
        String userName = "root";
        String passwd = "Ys20140913!";
        Shell shell = new Shell(ip, userName, passwd, 22);
        shell.execute("uname -s -r -v");

        ArrayList<String> stdout = shell.getStandardOutput();
        for (String str : stdout) {
            System.out.println(str);
        }
    }
}