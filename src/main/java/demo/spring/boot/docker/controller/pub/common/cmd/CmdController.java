package demo.spring.boot.docker.controller.pub.common.cmd;

import com.jcraft.jsch.JSchException;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TCommonCmdService;
import demo.spring.boot.docker.service.TRemoteHostService;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.util.ssh.Shell;
import demo.spring.boot.docker.vo.TCommonCmdVo;
import demo.spring.boot.docker.vo.TRemoteHostVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/commonCmd")
public class CmdController {

    @Autowired
    private TCommonCmdService tCommonCmdService;

    @ApiOperation(value = "添加common命令", notes = "添加common命令<br>" +
            "操作系统一般是：centos, ubuntu")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    example = "xxxx",
                    required = true)
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


    @Autowired
    private TUserService tUserService;

    @Autowired
    private SessionComponent sessionComponent;

    @Autowired
    private TRemoteHostService tRemoteHostService;


    /**
     * 添加host
     */
    @ApiOperation(value = "添加host", notes = "添加host<br>" +
            "需要提供IP+PORT+USER+PASSWD")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    example = "xxxx",
                    required = true)
    })
    @RequestMapping(value = {"/add2"}, method = RequestMethod.POST)
    public Response<Boolean> add2(@RequestParam(value = "ip", defaultValue = "39.107.236.187") String ip,
                                  @RequestParam(value = "username", required = false, defaultValue = "root") String username,
                                  @RequestParam(value = "password", required = false, defaultValue = "Ys20140913") String password,
                                  @RequestParam(value = "port", required = false, defaultValue = "22") String port,
                                  @RequestParam(value = "remark", required = false, defaultValue = "remark") String remark
    ) {
        TRemoteHostVo tRemoteHostVo = new TRemoteHostVo();
        tRemoteHostVo.setId(UUIDUtils.generateUUID());
        tRemoteHostVo.setBelongUserId(sessionComponent.getLoginUserVo().getId());
        tRemoteHostVo.setCreateTime(new Timestamp(new Date().getTime()));
        tRemoteHostVo.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        tRemoteHostVo.setStatus(UseStatus.IN_USE.getIndex());
        tRemoteHostVo.setHostIp(ip);
        tRemoteHostVo.setHostUser(username);
        tRemoteHostVo.setPasswd(password);
        tRemoteHostVo.setPort(port);
        tRemoteHostVo.setRemark(remark);
        boolean result = tRemoteHostService.insert(tRemoteHostVo);
        return Response.ok(result);
    }

    /**
     * 查询当前用户的所有主机 未删除的，在使用的主机
     */
    @ApiOperation(value = "查询当前用户的所有主机", notes = "查询当前用户的所有主机<br>")
    @RequestMapping(value = {"/queryAll"}, method = RequestMethod.GET)
    public Response<List<TRemoteHostVo>> queryAll() {
        TRemoteHostVo query = new TRemoteHostVo();
        query.setBelongUserId(sessionComponent.getLoginUserVo().getId());
        query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        query.setStatus(UseStatus.IN_USE.getIndex());
        List<TRemoteHostVo> tRemoteHostVos = tRemoteHostService.queryBase(query);
        return Response.ok(tRemoteHostVos);
    }

    /**
     * 根据id逻辑删除主机
     */
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    required = true)
    })
    @ApiOperation(value = "根据id逻辑删除主机", notes = "根据id逻辑删除主机")
    @RequestMapping(value = {"/deleteByHostId"}, method = RequestMethod.DELETE)
    public Response<Boolean> deleteByHostId(@RequestParam(value = "id") String hostId) {
        TRemoteHostVo source = new TRemoteHostVo();
        source.setStatus(DeleteStatus.HAS_DELETED.getIndex());
        TRemoteHostVo target = new TRemoteHostVo();
        target.setId(hostId);
        boolean bool = tRemoteHostService.updateBase(source, target);
        return Response.ok(bool);
    }

    /**
     * 根据ID修主机参数
     */
    @ApiOperation(value = "添加host", notes = "添加host<br>" +
            "需要提供IP+PORT+USER+PASSWD")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    example = "xxxx",
                    required = true)
    })
    @RequestMapping(value = {"/updateByHostId"}, method = RequestMethod.PATCH)
    public Response<Boolean> updateByHostId(@RequestParam(value = "id") String hostId,
                                            @RequestParam(value = "ip", defaultValue = "39.107.236.187") String ip,
                                            @RequestParam(value = "username", required = false, defaultValue = "root") String username,
                                            @RequestParam(value = "password", required = false, defaultValue = "Ys20140913") String password,
                                            @RequestParam(value = "port", required = false, defaultValue = "22") String port,
                                            @RequestParam(value = "remark", required = false, defaultValue = "remark") String remark
    ) {
        TRemoteHostVo target = new TRemoteHostVo();
        target.setId(hostId);
        TRemoteHostVo source = new TRemoteHostVo();
        source.setId(UUIDUtils.generateUUID());
        source.setBelongUserId(sessionComponent.getLoginUserVo().getId());
        source.setCreateTime(new Timestamp(new Date().getTime()));
        source.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        source.setStatus(UseStatus.IN_USE.getIndex());
        source.setHostIp(ip);
        source.setHostUser(username);
        source.setPasswd(password);
        source.setPort(port);
        source.setRemark(remark);
        boolean result = tRemoteHostService.updateBase(source, target);
        return Response.ok(result);
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

}
