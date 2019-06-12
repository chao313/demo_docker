package demo.spring.boot.docker.controller.pub.common.cmd;

import com.jcraft.jsch.JSchException;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TCommonCmdService;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.util.ssh.other.ShellSDK;
import demo.spring.boot.docker.vo.TCommonCmdVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/commonCmd")
public class CmdController {

    @Autowired
    private TCommonCmdService tCommonCmdService;

    @Autowired
    private SessionComponent sessionComponent;

    @ApiOperation(value = "添加common命令", notes = "添加common命令<br>" +
            "操作系统一般是：centos, ubuntu")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    example = "xxxx",
                    required = false)
    })
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Response<Boolean> add(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "remark", required = false) String remark,
            @RequestParam(value = "cmd", required = false) String cmd,
            @RequestParam(value = "dependent", required = false) String dependent,
            @RequestParam(value = "operatingSystem", required = false) String operatingSystem,
            @RequestParam(value = "upperVersion", required = false) String upperVersion,
            @RequestParam(value = "lowerVersion", required = false) String lowerVersion
    ) {
        TCommonCmdVo vo = new TCommonCmdVo();
        vo.setId(UUIDUtils.generateUUID());
        vo.setCreateTime(new Timestamp(new Date().getTime()));
        vo.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        vo.setName(name);
        vo.setRemark(remark);
        vo.setCmd(cmd);
        vo.setDependent(dependent);
        vo.setOperatingSystem(operatingSystem);
        vo.setUpperVersion(upperVersion);
        vo.setLowerVersion(lowerVersion);
        boolean bool = tCommonCmdService.insert(vo);
        return Response.ok(bool);
    }

    @ApiOperation(value = "执行指定shell的cmd", notes = "执行指定shell的cmd<br>")
    @RequestMapping(value = {"/executeCmdByCmdIdAndShellId"}, method = RequestMethod.GET)
    public Response executeCmdByCmdIdAndShellId(
            @RequestParam(value = "commondCmdId", required = true) String commondCmdId,
            @RequestParam(value = "shellId", required = true) String shellId
    ) throws IOException, JSchException {

        TCommonCmdVo tCommonCmdVo = tCommonCmdService.queryByPrimaryKey(commondCmdId, DeleteStatus.NOT_DELETED.getIndex());
        if (null == tCommonCmdVo) {
            return Response.fail("指定id不存在cmd");
        }
        sessionComponent.getShellSDK(shellId).execute(tCommonCmdVo.getCmd());
        return Response.ok(sessionComponent.getShellSDK(shellId).getStandardOutput());
    }

    @ApiOperation(value = "查询指定操作系统的命令", notes = "查询指定操作系统的命令<br>" +
            "操作系统一般是：centos, ubuntu")
    @RequestMapping(value = {"/queryByOperatingSystemAndLowerVersion"}, method = RequestMethod.GET)
    public Response<List<TCommonCmdVo>> queryByOperatingSystemAndLowerVersion(
            @RequestParam(value = "operatingSystem", required = false) String operatingSystem,
            @RequestParam(value = "lowerVersion", required = false) String lowerVersion
    ) {
        TCommonCmdVo query = new TCommonCmdVo();
        query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        query.setOperatingSystem(operatingSystem);
        query.setLowerVersion(lowerVersion);
        List<TCommonCmdVo> commonCmdVos = tCommonCmdService.queryBase(query);
        return Response.ok(commonCmdVos);
    }

    @ApiOperation(value = "执行指定shell的执行自定义命令", notes = "执行指定shell的执行自定义命令<br>")
    @RequestMapping(value = {"/executeCmdByShellId"}, method = RequestMethod.GET)
    public Response executeCmdByShellId(
            @RequestParam(value = "cmd", required = true) String cmd,
            @RequestParam(value = "shellId", required = true) String shellId
    ) throws IOException, JSchException, InterruptedException {
        ShellSDK shellSDK = sessionComponent.getShellSDK(shellId);
        if (null == shellSDK) {
            return Response.fail("当前shellId获取不到session中的shellSDK");
        }
        return Response.ok(sessionComponent.getShellSDK(shellId).executeSup(cmd));
    }


}
