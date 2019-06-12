package demo.spring.boot.docker.controller.pub;

import demo.spring.boot.docker.constant.Constant;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.util.ssh.other.ShellSDK;
import demo.spring.boot.docker.vo.cmd.response.DF;
import demo.spring.boot.docker.vo.cmd.response.Free;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/system")
public class SystemController {

    @Autowired
    private SessionComponent sessionComponent;

    @ApiOperation(value = "获取系统的内存使用", notes = "获取系统的内存使用<br>")
    @RequestMapping(value = {"/getSystemFreeByShellId"}, method = RequestMethod.GET)
    public Response getSystemFreeByShellId(
            @RequestParam(value = "shellId", required = true) String shellId) {
        ShellSDK shellSDK = sessionComponent.getShellSDK(shellId);
        if (null == shellSDK) {
            return Response.fail("当前shellId获取不到session中的shellSDK");
        }
        if (shellSDK.isConnect() == false) {
            return Response.fail("当前的shell已经失效，请重新登录");
        }
        List<String> list = sessionComponent.getShellSDK(shellId).executeSup(Free.CMD_FREE_H + Free.CMD_FREE_MEM);
        Free free = new Free();
        for (String line : list) {
            if (line.startsWith(Constant.RESPONSE_DATA)) {
                line = line.replace(Constant.RESPONSE_DATA, "");
                String[] split = line.split(",");
                free.setNewMem(split[0], split[1], split[2], split[3], split[4], split[5]);
            }
        }
        list = sessionComponent.getShellSDK(shellId).executeSup(Free.CMD_FREE_H + Free.CMD_FREE_SWAP);
        for (String line : list) {
            if (line.startsWith(Constant.RESPONSE_DATA)) {
                line = line.replace(Constant.RESPONSE_DATA, "");
                String[] split = line.split(",");
                free.setNewSwap(split[0], split[1], split[2]);
            }
        }
        return Response.ok(free);
    }

    @ApiOperation(value = "获取系统的磁盘使用", notes = "获取系统的磁盘使用<br>")
    @RequestMapping(value = {"/getSystemDFByShellId"}, method = RequestMethod.GET)
    public Response getSystemDFByShellId(
            @RequestParam(value = "shellId", required = true) String shellId) {
        ShellSDK shellSDK = sessionComponent.getShellSDK(shellId);
        if (null == shellSDK) {
            return Response.fail("当前shellId获取不到session中的shellSDK");
        }
        if (shellSDK.isConnect() == false) {
            return Response.fail("当前的shell已经失效，请重新登录");
        }
        List<String> list = sessionComponent.getShellSDK(shellId).executeSup(DF.CMD_DF_HL + DF.ATTACH_CMD);
        DF df = new DF();
        for (String line : list) {
            if (line.startsWith(Constant.RESPONSE_DATA)) {
                line = line.replace(Constant.RESPONSE_DATA, "");
                String[] split = line.split(",");
                df.addInfo(split[0], split[1], split[2], split[3], split[4], split[5]);
            }
        }
        return Response.ok(df);
    }


}
