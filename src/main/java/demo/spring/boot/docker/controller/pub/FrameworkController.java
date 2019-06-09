package demo.spring.boot.docker.controller.pub;

import demo.spring.boot.docker.framework.Code;
import demo.spring.boot.docker.framework.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2018/10/17    Created by   chao
 */
@RestController
@RequestMapping("/framework")
public class FrameworkController {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/response")
    public Response framework() {
        Response<Boolean> response = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            logger.info("SUCCESS:{}", true);
            response.setData(true);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("SUCCESS:{}", e.getMessage(), e);
        }
        return response;

    }
}
