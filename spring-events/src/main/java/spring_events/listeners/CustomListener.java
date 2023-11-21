package spring_events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring_events.events.CustomEvent;
import spring_events.events.SpecificEvent;

@Component
public class CustomListener {

    Logger log = LoggerFactory.getLogger(getClass());

    // Try to listen the event with type of 'CustomEvent'
    @EventListener
    void handleEvent(CustomEvent event) {
        log.info("[CustomListener] :: Handle the CustomEvent: " + event);

        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {}
    }

    // Try to listen the event with type of 'SpecificEvent'
    @EventListener
    void handleEvent(SpecificEvent event) {
        log.info("[CustomListener] :: Handle the SpecificEvent: " + event);

        try {
            Thread.sleep(4000);
        } catch (Exception ignore) {}
    }

}
