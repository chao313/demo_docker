package demo.spring.boot.docker.config.wesocket.javax;

import com.alibaba.fastjson.JSON;
import com.jcraft.jsch.JSchException;
import demo.spring.boot.docker.config.wesocket.javax.vo.Term2Vo;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.service.TRemoteHostService;
import demo.spring.boot.docker.util.ApplicationContextUtil;
import demo.spring.boot.docker.util.ssh.other.ShellSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * whc
 * 此websocket使用了javax包下的注解
 * <p>
 * 配置前后端的连接点，并重写配置信息
 * configurator = WebsocketConfig.class 该属性就是我上面提到我们可以自己配置的东西
 */
@ServerEndpoint(value = "/ws/websocket", configurator = WebsocketConfig.class)
@Component
public class WebSocket {
    private Logger log = LoggerFactory.getLogger(getClass());
    /*每个浏览器连接都会有一个新的会话对象*/
    private Session session;
    /*用来存储每个会话的session,静态的不会被实例化*/
    private static CopyOnWriteArraySet<WebSocket> webSocketSets = new CopyOnWriteArraySet<>();

    public static CopyOnWriteArraySet<WebSocket> getWebSocketSets() {
        return webSocketSets;
    }

    @Autowired
    private TRemoteHostService tRemoteHostService;
//WebApplicationContextUtils.getRequiredWebApplicationContext(((HttpSession) session.getUserProperties().get(WebsocketConfig.HTTP_SESSION)).getServletContext()).getBean(TRemoteHostService.class).queryByPrimaryKey("107cd0786025449ba3a5e1fe0acdadc5");

    @Autowired
    private ApplicationContextUtil applicationContextUtil;


    public Session getSession() {
        return session;
    }


    /**
     * 用来监听客户端连接建立，config用来获取WebsocketConfig中的配置信息
     * <p>
     * 使用webSocketSets记录下每一个连接的socketSession!!!
     *
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("config:{}", config.getUserProperties().get("name"));
        log.info("session:{}", config.getUserProperties().get("sessionid"));
        this.session = session;
        webSocketSets.add(this);
        log.info("##websocket消息## 有新的连接, 总数:{}", webSocketSets.size());
    }

    /**
     * 用来监听客户端关闭连接
     */
    @OnClose
    public void onClose() {
        webSocketSets.remove(this);
        log.info("##websocket消息## 连接断开, 总数:{}", webSocketSets.size());
    }

    /**
     * 用来监听连接关闭，或连接出错
     */
    @OnError
    public void onError(Throwable e, Session session) {
        webSocketSets.remove(this);
        log.info("##websocket消息## 连接出错或未关闭socket：" + e.getMessage());
        e.printStackTrace();

    }

    /**
     * 收到客户端发送的消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        Term2Vo term2Vo = JSON.parseObject(message, Term2Vo.class);
        if (null == session.getUserProperties().get("shell") && term2Vo.getTp().equals(Term2Vo.INIT)) {
            ShellSDK shellSDK = new ShellSDK(term2Vo.getDataHost(), term2Vo.getDataUsername(), term2Vo.getDataSecret(), term2Vo.getDataPort());
            try {
                session.getUserProperties().put("shell", shellSDK.login());
            } catch (JSchException e) {
                e.printStackTrace();
            }
            session.getAsyncRemote().sendText("ok");
            return;
        }
        byte[] response = ((ShellSDK) session.getUserProperties().get("shell")).executeSupBin(term2Vo.getMessage());
//        ByteBuffer wrap = ByteBuffer.wrap(response);
//        session.getAsyncRemote().sendBinary(wrap);

        session.getAsyncRemote().sendText(new String(response));
        log.info("##websocket消息## 收到客户端发来的消息:{}", message);
        log.info("##websocket消息## 发送的消息:{}", response);
        return;
    }

    /**
     * 获取httpSession中的
     *
     * @param ShellId
     * @return
     */
    private ShellSDK getShellSDKByShellId(String ShellId) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(WebsocketConfig.HTTP_SESSION);
        return ((Map<String, ShellSDK>) httpSession.getAttribute(SessionComponent.SHELLSDK_KEY)).get(ShellId);

    }


}