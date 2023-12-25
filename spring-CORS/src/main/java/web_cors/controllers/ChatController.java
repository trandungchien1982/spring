package web_cors.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import web_cors.dtos.ClientMessage;
import web_cors.dtos.ServerMessage;

@RestController
public class ChatController {

  Logger log = LoggerFactory.getLogger(getClass());

  int chatIdx = 0;

  @Autowired
  private SimpMessagingTemplate template;

  @GetMapping("/send-message")
  public String broadcastMessageFromServer() {
    chatIdx++;
    log.info("[ChatController] :: Try to broadcast a new message from Server, chatIdx: " + chatIdx + ", destination: /topic/greetings");

    template.setDefaultDestination("/topic/greetings");
    template.convertAndSend(new ServerMessage("Yeah! Custom Message :) ... " + chatIdx));
    return "[OK] :: send message to clients ... : " + chatIdx;
  }


  // When client publish a new message into /app/hello
  //	(with JSON structure : {name: "..."}
  //
  // --> The message will be handled by this method and we will deliver the ServerMessage to /topic/greetings
  // --> All subscriber of /topic/grettings will be received the JSON message from ServerMessage object :)
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public ServerMessage send(ClientMessage clientMsg) throws Exception {
    log.info("[ChatController] :: Received message from /chat : " + clientMsg);
    log.info("[ChatController] :: Routing message to all subscriber of /topic/messages");
    return new ServerMessage("Hello, " + HtmlUtils.htmlEscape(clientMsg.getName()));
  }
}
