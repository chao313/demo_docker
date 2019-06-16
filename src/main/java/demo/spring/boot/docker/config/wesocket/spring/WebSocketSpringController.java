package demo.spring.boot.docker.config.wesocket.spring;

import demo.spring.boot.docker.constant.SessionComponent;
import demo.spring.boot.docker.enums.DeleteStatus;
import demo.spring.boot.docker.enums.UseStatus;
import demo.spring.boot.docker.service.TUserService;
import demo.spring.boot.docker.util.UUIDUtils;
import demo.spring.boot.docker.vo.table.TUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Controller
public class WebSocketSpringController {

    @Autowired
    private TUserService tUserService;


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Map<String, SocketMessageSup> socketMessageSupsMap = new ConcurrentHashMap<>();
    //存放等待发送的消息
    private List<SocketMessageSup> socketMessageSupsWaitToSend = Collections.synchronizedList(new ArrayList<>());


    /**
     * 定时任务 -> 定时向前端推送数据
     *
     * @return
     * @throws Exception
     */
    @Scheduled(fixedRate = 10000)
    public Object callback() throws Exception {
        socketMessageSupsWaitToSend.forEach(socketMessageSup -> {
            messagingTemplate.convertAndSendToUser(socketMessageSup.getTarget().getUserId(),
                    "/topic/httpSendToServerToClientUser", socketMessageSup);
        });

        //比较socketMessageSupsMap中的消息，定时去除已经完成的消息
        //定时入库，防止消息丢失
        return "callback";
    }


    /**
     * client 使用http向指定主题推送消息 + 使用 messagingTemplate 由 server 向客户端推送消息
     * <p>
     * + 向指定用户推送消息
     * <p>
     * {@link SocketMessageSup.ALL}
     * {@link SocketMessageSup.DEPARTMENT}
     * {@link SocketMessageSup.PERSON}
     */
    @MessageMapping("/httpSendToServerToClientUser")
    @PostMapping("/httpSendToServerToClientUser")
    public String httpSendToServerToClientUser(@RequestBody SocketMessageSup msgSup) {
        if (null != msgSup.getUuid() && null != socketMessageSupsMap.get(msgSup.getUuid())) {
            //如果发送的消息，是回复消息 -> 因为UUID相同
            if (msgSup.getOriginUserId().equals(msgSup.getBack().getUserId())) {
                //如果回复人员和最先开始的人员保持一致 -> 自身确认消息 ->  直接终结消息
                this.removeMsg(msgSup.getUuid());
            } else {
                //如果回复人员和最先开始的人员不一致 -> 目标人员发送到服务器的回复
                if (msgSup.getNeedBack().equals("true")) {
                    //如果需要回复到消息发送方
                    messagingTemplate.convertAndSendToUser(msgSup.getOriginUserId(), "/topic/httpSendToServerToClientUser", msgSup);
                } else {
                    //不需要回复 -> 直接终结消息
                    this.removeMsg(msgSup.getUuid());
                }
            }
        } else {
            //如果发送的消息，新的消息
            msgSup.setUuid(UUIDUtils.generateUUID());
            msgSup.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            List<TUserVo> targertUsers = this.getTargetUsers(msgSup);//获取目标用户
            targertUsers.forEach(tsUserVo -> {
                SocketMessageSup socketMessageSup = new SocketMessageSup();
                /**
                 * 这里没有进行深度拷贝!!!!
                 */
                Target targetTargetVo = new Target();
                Back targetBackVo = new Back();
                BeanUtils.copyProperties(msgSup, socketMessageSup);
                BeanUtils.copyProperties(msgSup.getTarget(), targetTargetVo);
                BeanUtils.copyProperties(msgSup.getBack(), targetBackVo);
                targetTargetVo.setUserId(tsUserVo.getId());
                socketMessageSup.setBack(targetBackVo);
                socketMessageSup.setTarget(targetTargetVo);
                socketMessageSupsWaitToSend.add(socketMessageSup);//生产等待发送的消息
            });
            for (WebSocketSession socketSession : MyWebSocketHandlerDecorator.getWebSocketSets()) {
                socketMessageSupsWaitToSend.forEach(socketMessageSup -> {
                    //遍历所有的消息，去发送
                    //获取session中的用户
                    Object userObject = ((HttpSession) socketSession.getAttributes()
                            .get((MyHandlerShareInterceptor.HTTP_SESSION))).getAttribute(SessionComponent.LOGIN_USER);
                    if (null != userObject) {
                        if (socketMessageSup.getTarget().getUserId().equals(((TUserVo) userObject).getId())) {
                            //如果发现目标用户中id，和session中的用户id相同 -> 发送消息
                            messagingTemplate.convertAndSendToUser(socketMessageSup.getTarget().getUserId(), "/topic/httpSendToServerToClientUser", msgSup);
                            //发送完成不需要处理 ，发送是否成功在上面的逻辑处理
                        }

                    }

                });
            }

        }
        return msgSup + "使用http发送消息成功";
    }

    /**
     * 获取目标用户
     *
     * @param msgSup
     * @return
     */
    private List<TUserVo> getTargetUsers(final SocketMessageSup msgSup) {
        List<TUserVo> targertUsers = new ArrayList<>();
        if (null != msgSup.getTarget()) {
            if (SocketMessageSup.ALL.equalsIgnoreCase(msgSup.getTarget().getLevel())) {
                //如果消息要发给所有人
                TUserVo query = new TUserVo();
                query.setStatus(UseStatus.IN_USE.getIndex());
                query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
                List<TUserVo> tUserVos = tUserService.queryBase(query);
                targertUsers.addAll(tUserVos);
            } else if (SocketMessageSup.DEPARTMENT.equalsIgnoreCase(msgSup.getTarget().getLevel())) {
                //如果消息要发给指定类型的人
                /**
                 * todo something...
                 */
                //
            } else if (SocketMessageSup.PERSON.equalsIgnoreCase(msgSup.getTarget().getLevel())) {
                //如果消息要发给指定的人
                msgSup.getTarget().getIds().forEach(userID -> {
                    TUserVo query = new TUserVo();
                    query.setId(userID);
                    query.setStatus(UseStatus.IN_USE.getIndex());
                    query.setDeleteFlag(DeleteStatus.NOT_DELETED.getIndex());
                    List<TUserVo> tUserVos = tUserService.queryBase(query);
                    targertUsers.addAll(tUserVos);
                });
            }
        }
        return targertUsers;
    }

    /**
     * 移除相关的uuid下的消息
     *
     * @param msgSupUuid
     */
    private void removeMsg(String msgSupUuid) {
        socketMessageSupsMap.remove(msgSupUuid);
        socketMessageSupsWaitToSend.stream().filter(socketMessageSup -> socketMessageSup.getUuid().equals(msgSupUuid)).forEach(socketMessageSup -> socketMessageSupsWaitToSend.remove(socketMessageSup));
    }


}
