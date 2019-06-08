package demo.spring.boot.docker.controller.pub.login;

import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.framework.Code;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.vo.TUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private TUserService tUserService;

    /**
     * 同时两个url
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public Response login(@RequestParam(value = "name") String name,
                          @RequestParam(value = "passwd") String passwd
    ) {
        Response<Boolean> response = new Response<>();
        TUserVo query = new TUserVo();
        query.setName(name);
        query.setPassword(passwd);
        query.setStatus(UseStatus.IN_USE.getIndex());
        query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
        List<TUserVo> tUserVos = tUserService.queryBase(query);
        if (tUserVos.size() == 1) {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(true);
        } else {
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            response.setContent(false);
        }
        return response;
    }
}
