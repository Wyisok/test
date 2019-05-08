package park.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class MyHandler implements WebSocketHandler {
	//在线用户列表
    private static final Map<String, WebSocketSession> users;
    //用户标识
    private static final String CLIENT_ID = "username";
    static {
        users = new HashMap<String, WebSocketSession>();
    }
    
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1) throws Exception {
		System.out.println("连接已关闭");

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession arg0) throws Exception {
		 System.out.println("成功建立连接");

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message ) throws Exception {
        // ...
        System.out.println(message.getPayload());

        WebSocketMessage message1 = new TextMessage("server:"+message);
        try {
            session.sendMessage(message1);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1) throws Exception {
		 System.out.println("连接出错");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
