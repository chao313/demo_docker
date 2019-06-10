package demo.spring.boot.docker.controller.pub.login;

import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.framework.Code;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.util.MD5Utils;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.TUserVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private TUserService tUserService;

    @Autowired
    private SessionComponent sessionComponent;

    /**
     * 登陆
     */
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(
                    name = "X-CSRF-TOKEN",
                    value = "用户Token",
                    dataType = "string",
                    paramType = "header",
                    example = "xxxx",
                    required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")
    })
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public Response login(@RequestParam(value = "name") String name,
                          @RequestParam(value = "passwd") String passwd
    ) {
        TUserVo query = new TUserVo();
        query.setName(name);
        query.setPassword(passwd);
        query.setStatus(UseStatus.IN_USE.getIndex());
        query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        List<TUserVo> tUserVos = tUserService.queryBase(query);
        if (tUserVos.size() == 1) {
            sessionComponent.setLoginUserVo(tUserVos.get(0));
            return Response.ok(true);
        } else {
            return new Response(Code.UserErrors.USER_NOT_FOUND, Code.UserErrors.USER_NOT_FOUND_MSG, false);
        }
    }

    /**
     * 注销
     */
    @RequestMapping(value = {"/loginOff"}, method = RequestMethod.POST)
    public Response loginOff() {
        sessionComponent.loginOff();
        return Response.ok(true);
    }


    /**
     * 注册
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public Response addUser(@RequestParam(value = "name") String name,
                            @RequestParam(value = "passwd") String passwd
    ) {
        TUserVo query = new TUserVo();
        query.setName(name);
        query.setStatus(UseStatus.IN_USE.getIndex());
        query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        List<TUserVo> tUserVos = tUserService.queryBase(query);
        if (tUserVos.size() > 0) {
            return Response.fail(false, "注册的用户名已经存在");
        } else {
            String id = UUIDUtils.generateUUID();
            query.setId(id);
            query.setPassword(passwd);
            query.setSalt(MD5Utils.encodeByMD5(id));
            query.setCreateTime(new Timestamp(new Date().getTime()));
            boolean bool = tUserService.insert(query);
            return Response.ok(bool, "注册成功");
        }
    }

}
