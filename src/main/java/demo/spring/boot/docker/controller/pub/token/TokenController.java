package demo.spring.boot.docker.controller.pub.token;

import demo.spring.boot.docker.config.security.MyHttpSessionCsrfTokenRepository;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.framework.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/Token")
public class TokenController {

    @Autowired
    private MyHttpSessionCsrfTokenRepository csrfTokenRepository;

    @Autowired
    private SessionComponent sessionComponent;

    /**
     * 如果存在，获取当前的 CsrfToken
     * 如果不存在 ，生成新的 CsrfToken
     */
    @ApiOperation(value = "生成CsrfToken", notes = "如果存在，获取当前的 CsrfToken <br> 如果不存在 ，生成新的 CsrfToken")
    @RequestMapping(value = {"/TokenCsrfGetNotReplace"}, method = RequestMethod.GET)
    public Response getCsrfTokenNotReplace(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = csrfTokenRepository.generateTokenNotReplace(request);
        csrfTokenRepository.saveToken(token, request, response);
        return Response.ok(sessionComponent.getCsrfToken());
    }

    @ApiOperation(value = "生成CsrfToken", notes = "存在不存在都会生成新的 CsrfToken")
    @RequestMapping(value = {"/TokenCsrfGet"}, method = RequestMethod.GET)
    public Response getCsrfToken(HttpServletRequest request, HttpServletResponse response) {
        CsrfToken token = csrfTokenRepository.generateToken(request);
        csrfTokenRepository.saveToken(token, request, response);
        return Response.ok(sessionComponent.getCsrfToken());
    }

}
