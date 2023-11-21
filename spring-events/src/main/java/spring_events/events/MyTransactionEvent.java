package spring_events.events;

import lombok.Data;

@Data
public class MyTransactionEvent {
    String eventTransId;
    String eventTransMsg;
}
