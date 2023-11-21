package spring_events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring_events.events.SpecificEvent;

@Component
public class ApplicationListener {

    Logger log = LoggerFactory.getLogger(getClass());

    // Try to listen the event with type of 'CustomEvent'
    @EventListener
    void handleEvent(ApplicationEvent event) {
        log.info("[ApplicationListener] :: Handle the ApplicationEvent: " + event);

        try {
            Thread.sleep(10);
        } catch (Exception ignore) {}
    }

}
