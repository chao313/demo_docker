package demo.spring.boot.docker.filter;

import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.framework.Code;
import demo.spring.boot.docker.framework.Response;
import demo.spring.boot.docker.vo.table.TUserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @desc: 获取用户信息及用户权限，并存放于本地线程变量中过滤器
 * @author: cm
 * @createTime: 2019年05月08日 下午11:46:01
 * @version: v1.0
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "userInfoFilter")
public class UserInfoFilter implements Filter {

    @Autowired
    private SessionComponent sessionComponent;

    private static Logger LOGGER = LoggerFactory.getLogger(UserInfoFilter.class);

    // 用户信息在session中的key
    private static final String USER_KEY_IN_SESSION = "userInfo";

    // 角色信息在session中的key
    private static final String ROLE_KEY_IN_SESSION = "roleInfo";

    // 例外的 URL
    private String excludeUrl = "/demo_docker/favicon.ico /demo_docker/user/login /demo_docker/Token/TokenCsrfGetNotReplace /demo_docker/Token/TokenCsrfGet /demo_docker/index.html /index.html /demo_docker/swagger-ui.html /demo_docker/swagger-resources/configuration/ui /demo_docker/v2/api-docs";
    private String staticStartUrl = "/demo_docker/static";
    private String fileStartUrl = "/demo_docker/file";
    private String swaggerUrl = "/demo_docker/webjars";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rsp = (HttpServletResponse) servletResponse;
        // 获取URL
        String url = req.getRequestURI();
//        LOGGER.info("访问路径:{}", url);
//        LOGGER.info("排除访问路径:{}", excludeUrl);
        // 如果是例外的URL，比如登录地址，则不用进行过滤
        if (StringUtils.isBlank(url)
                || excludeUrl.contains(url)
                || url.startsWith(staticStartUrl)
                || url.startsWith(fileStartUrl)
                || url.startsWith(swaggerUrl)) {
//            LOGGER.info("访问例外的URL，管理端不用进行过滤");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

//        LOGGER.info("需要过滤的地址----");
        TUserVo tUserVo = sessionComponent.getLoginUserVo();
        if (tUserVo == null) {
            Response res = new Response(Code.UserErrors.USER_NOT_LOGIN, Code.UserErrors.USER_NOT_LOGIN_MSG, null);
            try {
                HttpServletResponse response = (HttpServletResponse) rsp;
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(JSON.toJSONString(res));
            } catch (IOException ioe) {
                LOGGER.error(ioe.toString(), ioe);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}