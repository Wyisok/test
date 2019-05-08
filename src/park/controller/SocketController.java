package park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import park.websocket.MyHandler;

@Controller
public class SocketController {
	@Autowired
    MyHandler handler;
	
	
}
