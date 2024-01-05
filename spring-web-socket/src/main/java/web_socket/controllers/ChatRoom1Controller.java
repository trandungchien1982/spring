package web_socket.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import web_socket.dtos.ClientMessage;
import web_socket.dtos.ServerMessage;

@RestController
public class ChatRoom1Controller {

  Logger log = LoggerFactory.getLogger(getClass());

  int chatIdx = 0;

  @Autowired
  private SimpMessagingTemplate template;

  @GetMapping("/room1/server-notify")
  public String broadcastMessageFromServer() {
    chatIdx++;
    log.info("[ChatRoom1] :: Try to broadcast a new message from Server, chatIdx: " + chatIdx + ", destination: /topic/greetings/room1");

    String destinationTopic = "/topic/greetings/room1";
    template.convertAndSend(destinationTopic, new ServerMessage("Yeah! Custom Message in ChatRoom 1 :) ... " + chatIdx));
    return "[ChatRoom1] :: send message to clients ... : " + chatIdx;
  }


  // When client publish a new message into /app/hello
  //	(with JSON structure : {name: "..."}
  //
  // --> The message will be handled by this method and we will deliver the ServerMessage to /topic/greetings
  // --> All subscriber of /topic/grettings will be received the JSON message from ServerMessage object :)
  @MessageMapping("/hello-chat1")
  @SendTo("/topic/greetings/room1")
  public ServerMessage send(ClientMessage clientMsg) {
    log.info("[ChatRoom1Controller] :: Received message from /chat : " + clientMsg);
    log.info("[ChatRoom1Controller] :: Routing message to all subscriber of /topic/greetings/room1");
    return new ServerMessage("Message for ChatRoom01, " + HtmlUtils.htmlEscape(clientMsg.getName()));
  }
}
