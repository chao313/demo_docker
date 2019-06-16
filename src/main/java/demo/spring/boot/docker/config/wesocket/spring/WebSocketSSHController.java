package demo.spring.boot.docker.config.wesocket.spring;

import demo.spring.boot.docker.config.wesocket.spring.vo.TermJsVo;
import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.table.TUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 专门留作term.js使用
 */
@Controller
public class WebSocketSSHController {


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SessionComponent sessionComponent;




    /**
     * client 使用http向指定主题推送消息 + 使用 messagingTemplate 由 server 向客户端推送消息
     * <p>
     */
    @MessageMapping("/sshTermJs")
    @SendTo("/sshTermJs")
    public byte[] httpSendToServerToClientUser(@RequestBody TermJsVo termJsVo) {
        byte[] response = sessionComponent.getShellSDK(termJsVo.getShellId()).executeSupBin(termJsVo.getMessage());
        return response;
    }


}
