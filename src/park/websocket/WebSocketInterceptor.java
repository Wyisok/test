package park.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler hander,
			Map<String, Object> map) throws Exception {
//		if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
//            HttpSession session = serverHttpRequest.getServletRequest().getSession();
//            if (session != null) {
//                map.put("username", session.getAttribute("username"));
//            }
//        }
        return true;
	}

}
