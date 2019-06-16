package demo.spring.boot.docker.controller.pub;

import demo.spring.boot.docker.constant.Constant;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.util.ssh.other.ShellSDK;
import demo.spring.boot.docker.vo.cmd.response.DF;
import demo.spring.boot.docker.vo.cmd.response.Free;
import demo.spring.boot.docker.vo.cmd.response.LS;
import demo.spring.boot.docker.vo.cmd.response.PS;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * @param shellId
     * @param order   排序方式
     * @param head    查询前n个
     * @return
     */
    @ApiOperation(value = "获取系统的PS相关信息", notes = "获取系统的PS相关信息" +
            "<br>" +
            "order 3为MEM,4为cpu" +
            "<br>" +
            "head 存在就限制，不存在就不限制")
    @RequestMapping(value = {"/getSystemPSByShellId"}, method = RequestMethod.GET)
    public Response getSystemPSByShellId(
            @RequestParam(value = "shellId", required = true) String shellId,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "head", required = false) String head
    ) {
        ShellSDK shellSDK = sessionComponent.getShellSDK(shellId);
        if (null == shellSDK) {
            return Response.fail("当前shellId获取不到session中的shellSDK");
        }
        if (shellSDK.isConnect() == false) {
            return Response.fail("当前的shell已经失效，请重新登录");
        }
        String cmd = PS.CMD;
        if (StringUtils.isNotBlank(order)) {
            cmd += PS.ORBER_CMD + order;
        }
        if (StringUtils.isNotBlank(head)) {
            cmd += PS.HEAD + head;
        }
        cmd += PS.AWK;
        List<String> list = sessionComponent.getShellSDK(shellId).executeSup(cmd);
        PS ps = new PS();
        for (String line : list) {
            if (line.startsWith(Constant.RESPONSE_DATA)) {
                line = line.replace(Constant.RESPONSE_DATA, "");
                String[] split = line.split(",");
                ps.addProcessInfo(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7], split[8], split[9], split[10]);
            }
        }
        return Response.ok(ps);
    }

    @ApiOperation(value = "获取系统的LS相关信息", notes = "获取系统的LS相关信息" +
            "<br>" +
            "path : 指定路径")
    @RequestMapping(value = {"/getPathLSByShellId"}, method = RequestMethod.GET)
    public Response getSystemPSByShellId(
            @RequestParam(value = "shellId", required = true) String shellId,
            @RequestParam(value = "path", required = false) String path
    ) {
        ShellSDK shellSDK = sessionComponent.getShellSDK(shellId);
        if (null == shellSDK) {
            return Response.fail("当前shellId获取不到session中的shellSDK");
        }
        if (shellSDK.isConnect() == false) {
            return Response.fail("当前的shell已经失效，请重新登录");
        }
        String cmd = "";
        if (StringUtils.isNotBlank(path)) {
            cmd = LS.generateCMD(path);
        }
        List<String> list = sessionComponent.getShellSDK(shellId).executeSup(cmd);
        LS ls = new LS();
        for (String line : list) {
            if (line.startsWith(Constant.RESPONSE_DATA)) {
                line = line.replace(Constant.RESPONSE_DATA, "");
                String[] split = line.split(",");
                if (split[0].length() == 10) {

                    ls.addFileInfo(split[0].substring(0, 1), split[0].substring(1, 4), split[0].substring(4, 7), split[0].substring(7, 10), split[1], split[2], split[3], split[4], split[5] + " " + split[6], split[7]);
                }
            }
        }
        return Response.ok(ls);
    }


}
