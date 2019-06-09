package demo.spring.boot.docker.controller.aspect;

import demo.spring.boot.docker.framework.Code;
import demo.spring.boot.docker.framework.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * execution的切面
 * 2018/10/18    Created by   chao
 */
@RestController
@RequestMapping("/executionAspect")
public class ExecutionAspectController implements AspectController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "测试execution表达式的拦截", notes = "测试execution表达式的拦截")
    @GetMapping("/aspect")
    public Response framework(@RequestParam(value = "one") Integer one,
                              @RequestParam(value = "two") Integer two) {
        Response response = new Response();
        response.setCode(Code.System.OK);
        response.setMsg(Code.System.SERVER_SUCCESS_MSG);
        response.setData(one / two);
        return response;
    }

    @Override
    public void BeforeAspectMethod() {
        logger.info("【切面调用：】函数之前");
    }

    @Override
    public void AfterAspectMethod() {
        logger.info("【切面调用：】函数之后");
    }
}
