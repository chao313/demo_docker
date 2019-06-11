package demo.spring.boot.docker.util.ssh;

import java.util.ArrayList;
import java.util.List;

public interface ShellSDKInterface {


    /**
     * 登陆shell
     *
     * @return
     */
    ShellSDKInterface login(String ip, String username, String password, int port);


    /**
     * 获取登陆后的shellId
     * 此Id应由子类实现UUID
     *
     * @return
     */
    String getId();


    /**
     * 执行命令 立刻返回输出值
     *
     * @param command
     * @return
     */
    List<String> executeSync(final String command);

    /**
     * 执行命令 异步返回输出值
     *
     * @param command
     * @return
     */
    int executeAsync(final String command);


    /**
     * 关闭当前连接
     */
    void close();


    /**
     * 获取连接状态
     */
    boolean isConnect();

    /**
     * 获取标准输出
     *
     * @return
     */
    ArrayList<String> getStandardOutput();

}