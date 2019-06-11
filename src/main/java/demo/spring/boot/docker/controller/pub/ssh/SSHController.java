package demo.spring.boot.docker.controller.pub.ssh;


import com.jcraft.jsch.JSchException;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TRemoteHostService;
import demo.spring.boot.docker.util.ssh.Shell;
import demo.spring.boot.docker.util.ssh.ShellSDK;
import demo.spring.boot.docker.vo.TRemoteHostVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * SSH
 */
@RestController
@RequestMapping(value = "/shell")
public class SSHController {

    @Autowired
    private TRemoteHostService tRemoteHostService;

    @Autowired
    private SessionComponent sessionComponent;

    @GetMapping(value = "/execute")
    public ArrayList<String> test(
            @RequestParam(value = "ip", defaultValue = "39.107.236.187") String ip,
            @RequestParam(value = "username", required = false, defaultValue = "root") String username,
            @RequestParam(value = "password", required = false, defaultValue = "Ys20140913") String password,
            @RequestParam(value = "port", required = false, defaultValue = "22") int port,
            @RequestParam(value = "cmd") String cmd
    ) {
        Shell shell = new Shell(ip, username, password, port);
        try {
            shell.execute(cmd);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        ArrayList<String> standardOutput = shell.getStandardOutput();
        return standardOutput;
    }

    @ApiOperation(value = "根据id执行host上的命令", notes = "根据id执行host上的命令<br>ls/cd/free")
    @RequestMapping(value = {"/executeCmdByHostId"}, method = RequestMethod.GET)
    public Response<ArrayList<String>> executeCmdByHostId(@RequestParam(value = "id") String hostId,
                                                          @RequestParam(value = "cmd") String cmd) {
        TRemoteHostVo vo = tRemoteHostService.queryByPrimaryKey(hostId);
        Shell shell = new Shell();
        try {
            shell.initByTRemoteHostVo(vo).execute(cmd);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        ArrayList<String> standardOutput = shell.getStandardOutput();
        return Response.ok(standardOutput);
    }

    @ApiOperation(value = "登陆Shell", notes = "登陆Shell<br>登陆的shell会放入session<br>会返回shell的id")
    @RequestMapping(value = {"/loginByHostId"}, method = RequestMethod.POST)
    public Response<String> loginByHostId(@RequestParam(value = "id") String hostId) {
        TRemoteHostVo vo = tRemoteHostService.queryByPrimaryKey(hostId);
        ShellSDK shell = new ShellSDK();
        try {
            shell = new ShellSDK(vo);
            shell.login();//登陆
            sessionComponent.putShellSDK(shell.getId(), shell);//当前登陆放入session的map
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return Response.ok(shell.getId());
    }

    @ApiOperation(value = "获取所有已经登陆Shell", notes = "获取所有已经登陆Shell<br>从session获取")
    @RequestMapping(value = {"/queryShellAll"}, method = RequestMethod.POST)
    public Response<List<ShellSDK>> queryShellAll() {
        //当前登陆放入session的map的value
        return Response.ok(sessionComponent.getAllShellSDK());
    }

}