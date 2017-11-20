package tms.spring.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArraySet;
import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


/**
 * Created by user on 2017/11/20.
 */
@ServerEndpoint("/webSocket")
public class WebSocketUtil {

    private static Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<WebSocketUtil>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 连接建立成功调用的方法
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线数加
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减
        logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息:"+session.getId()+"," + message);
        for(WebSocketUtil item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.warn("向客户端发送消息失败:"+item.session.getId()+"," +e.getMessage());
                continue;
            }
        }
    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.warn("socket连接出错:"+session.getId()+"," +error.getMessage());
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketUtil.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketUtil.onlineCount--;
    }

    public static void broadcast(String message){
        for(WebSocketUtil item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                logger.warn("向客户端发送消息失败:"+item.session.getId()+"," +e.getMessage());
                continue;
            }
        }
    }
}
