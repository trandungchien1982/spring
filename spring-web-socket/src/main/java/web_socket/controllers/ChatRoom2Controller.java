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
public class ChatRoom2Controller {

  Logger log = LoggerFactory.getLogger(getClass());

  int chatIdx = 0;

  @Autowired
  private SimpMessagingTemplate template;

  @GetMapping("/room2/server-notify")
  public String broadcastMessageFromServer() {
    chatIdx++;
    log.info("[ChatRoom2] :: Try to broadcast a new message from Server, chatIdx: " + chatIdx + ", destination: /topic/greetings/room2");

    String destinationTopic = "/topic/greetings/room2";
    template.convertAndSend(destinationTopic, new ServerMessage("OK! My Message in ChatRoom 2 :) ... " + chatIdx));
    return "[ChatRoom2] :: send message to clients ... : " + chatIdx;
  }


  // When client publish a new message into /app/hello-chat2
  //	(with JSON structure : {name: "..."}
  //
  // --> The message will be handled by this method and we will deliver the ServerMessage to /topic/greetings/room2
  // --> All subscriber of /topic/greetings/room2 will be received the JSON message from ServerMessage object :)
  @MessageMapping("/hello-chat2")
  @SendTo("/topic/greetings/room2")
  public ServerMessage send(ClientMessage clientMsg) {
    log.info("[ChatRoom2Controller] :: Received message from /chat : " + clientMsg);
    log.info("[ChatRoom2Controller] :: Routing message to all subscriber of /topic/greetings/room2");
    return new ServerMessage("Thông điệp từ ChatRoom2, " + HtmlUtils.htmlEscape(clientMsg.getName()));
  }
}
