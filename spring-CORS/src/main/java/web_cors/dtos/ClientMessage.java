package web_cors.dtos;

import lombok.Data;

@Data
public class ClientMessage {
  // We have to declare the default constructor for auto-deserialize JSON data
  // Using @AllArgsConstructor does not help :(
  public ClientMessage() {}

  String name;
}
