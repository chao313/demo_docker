package demo.spring.boot.docker.constant;

import demo.spring.boot.docker.util.ssh.other.ShellSDK;
import demo.spring.boot.docker.vo.table.TUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionComponent {

    @Autowired
    private HttpSession httpSession;

    public static String LOGIN_USER = "login_user";

    public static String CSRF_TOKEN_KEY = "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";

    public static String SHELLSDK_KEY = "shellsdk_key";

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

    /**
     * 获取session中的CsrfToken
     * token ->
     * parameterName -> _csrf
     * headerName -> X-CSRF-TOKEN
     *
     * @return
     */
    public DefaultCsrfToken getCsrfToken() {
        return (DefaultCsrfToken) httpSession.getAttribute(SessionComponent.CSRF_TOKEN_KEY);
    }

    /**
     * 获取 session 中的shellSDKMAP
     * 如果为null，就新建一个
     * <p>
     * !!!后期线程并发可能导问题
     * <p>
     * 使用了双重校验锁-> 应该可以解决多线程问题
     *
     * @return
     */
    public void putShellSDK(String key, ShellSDK shellSDK) {
        Object object = httpSession.getAttribute(SessionComponent.SHELLSDK_KEY);
        if (null == object) {             //第一次判断 ->为了提高效率，不必每次同步
            synchronized (SessionComponent.class) { //内部同步代码块
                if (null == object) {     //第二次判断 -> 为了防止创建多个实例
                    object = new ConcurrentHashMap<>();
                    httpSession.setAttribute(SessionComponent.SHELLSDK_KEY, object);
                }
            }
        }
        ((Map<String, ShellSDK>) httpSession.getAttribute(SessionComponent.SHELLSDK_KEY)).put(key, shellSDK);
    }

    /**
     * 设置 session 中的shellSDKMAP
     * !!!后期线程并发可能导问题
     * <p>
     * 改为了ConcurrentHashMap -> 应该可以解决多线程问题
     */
    public ShellSDK getShellSDK(String key) {
        return ((Map<String, ShellSDK>) httpSession.getAttribute(SessionComponent.SHELLSDK_KEY)).get(key);
    }

    /**
     * 设置 session 中的shellSDKMAP
     * !!!后期线程并发可能导问题
     * <p>
     * 改为了ConcurrentHashMap -> 应该可以解决多线程问题
     */
    public List<ShellSDK> getAllShellSDK() {
        Object object = httpSession.getAttribute(SessionComponent.SHELLSDK_KEY);
        if (null == object) {             //第一次判断 ->为了提高效率，不必每次同步
            synchronized (SessionComponent.class) { //内部同步代码块
                if (null == object) {     //第二次判断 -> 为了防止创建多个实例
                    object = new ConcurrentHashMap<>();
                    httpSession.setAttribute(SessionComponent.SHELLSDK_KEY, object);
                }
            }
        }
        Collection<ShellSDK> values = ((Map<String, ShellSDK>) httpSession.getAttribute(SessionComponent.SHELLSDK_KEY)).values();
        return new ArrayList<>(values);
    }

}
