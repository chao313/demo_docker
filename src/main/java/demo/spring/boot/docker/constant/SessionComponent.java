package demo.spring.boot.docker.constant;

import demo.spring.boot.docker.vo.TUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionComponent {

    @Autowired
    private HttpSession httpSession;

    private static String LOGIN_USER = "login_user";

    /**
     * 获取 session 中的登陆信息
     *
     * @return
     */
    public TUserVo getLoginUserVo() {
        return (TUserVo) httpSession.getAttribute(SessionComponent.LOGIN_USER);
    }

    /**
     * 设置 session 登陆信息
     *
     * @param tUserVo
     */
    public void setLoginUserVo(TUserVo tUserVo) {
        httpSession.setAttribute(SessionComponent.LOGIN_USER, tUserVo);
    }

    /**
     * 设置 session 登陆信息
     */
    public void loginOff() {
        httpSession.invalidate();
    }
}
