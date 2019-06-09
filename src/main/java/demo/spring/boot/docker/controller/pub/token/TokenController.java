package demo.spring.boot.docker.controller.pub.token;

import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.util.MD5Utils;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.TUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/csrfToken")
public class TokenController {


    @Autowired
    private SessionComponent sessionComponent;

    /**
     * 获取当前的CsrfToken
     */
    @RequestMapping(value = {"/get"}, method = RequestMethod.GET)
    public Response getCsrfToken(
    ) {
        return Response.ok(sessionComponent.getCsrfToken());
    }

}
