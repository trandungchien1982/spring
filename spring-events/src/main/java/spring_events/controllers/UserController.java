package spring_events.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import spring_events.entities.User;
import spring_events.events.MyTransactionEvent;
import spring_events.events.CustomEvent;
import spring_events.events.SpecificEvent;
import spring_events.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(path="/get-users")
    public List<User> listUsers() {
        return userService.getListUsers();
    }

    @GetMapping(path="/add-user")
    public String addUsers() {
        return userService.addUser();
    }

    @GetMapping(path="/publish-custom-event")
    public String publishCustomEvent() {
        CustomEvent newCustomEvent = new CustomEvent();
        long id = System.currentTimeMillis();
        newCustomEvent.setEventId("ID: " + id);
        newCustomEvent.setEventMsg("MSG: " + id);

        log.info("[UserController] ----------------------------------------------------------");
        log.info("[UserController] Publish Custom event id: " + id);
        applicationEventPublisher.publishEvent(newCustomEvent);

        log.info("[UserController] Return the response string for custom id: " + id);
        return "FINISH publish custom event id: " + id;
    }

    @GetMapping(path="/publish-specific-event")
    public String publishSpecificEvent() {
        SpecificEvent newSPEvent = new SpecificEvent();
        long id = System.currentTimeMillis();
        newSPEvent.setSpEventId ("Sp-ID: " + id);
        newSPEvent.setSpEventMsg("Sp-MSG: " + id);

        log.info("[UserController] ----------------------------------------------------------");
        log.info("[UserController] Publish Specific event id: " + id);
        applicationEventPublisher.publishEvent(newSPEvent);

        log.info("[UserController] Return the response string for specific id: " + id);
        return "FINISH publish Specific event id: " + id;
    }

    @GetMapping(path="/publish-add-user-no-transaction")
    public String publishAddUserWithoutTransaction() {
        log.info("[UserController] ----------------------------------------------------------");
        log.info("[UserController] Try to add new user with NO transaction: ");
        long evtId = System.currentTimeMillis();
        MyTransactionEvent myTransactionEvent = new MyTransactionEvent();
        myTransactionEvent.setEventTransId("eventTransID: " + evtId);
        myTransactionEvent.setEventTransMsg("eventTransMsg: " + evtId);
        applicationEventPublisher.publishEvent(myTransactionEvent);
        userService.addUser();
        log.info("[UserController] Return the response string for add new user with NO transaction  ");
        return "FINISH publish add user [NO-Transaction]: " + evtId;
    }

    @GetMapping(path="/publish-add-user-transaction")
    @Transactional
    public String publishAddUserWithTransaction() {
        long evtId = System.currentTimeMillis();
        log.info("[UserController] ----------------------------------------------------------");
        log.info("[UserController] Try to add new user within transaction: " + evtId);
        MyTransactionEvent myTransactionEvent = new MyTransactionEvent();
        myTransactionEvent.setEventTransId("eventTransID: " + evtId);
        myTransactionEvent.setEventTransMsg("eventTransMsg: " + evtId);
        applicationEventPublisher.publishEvent(myTransactionEvent);
        userService.addUser();
        log.info("[UserController] Return the response string for add new user within transaction  ");
        return "FINISH publish add user [Transaction]: " + evtId;
    }

    @GetMapping(path="/publish-add-user-rollback")
    @Transactional
    public String publishAddUserWithRollback() {
        long evtId = System.currentTimeMillis();
        log.info("[UserController] ----------------------------------------------------------");
        log.info("[UserController] Try to add new user with rollback: " + evtId);

        MyTransactionEvent myTransactionEvent = new MyTransactionEvent();
        myTransactionEvent.setEventTransId("eventTransID: " + evtId);
        myTransactionEvent.setEventTransMsg("eventTransMsg: " + evtId);
        applicationEventPublisher.publishEvent(myTransactionEvent);
        try {
            userService.addUserWithError();
        } catch (Exception ex) {
            log.error("[UserController] :: Exception occur {}", ex);
        }

        log.info("[UserController] Return the response string for add new user with Rollback");
        return "FINISH publish add user [Rollback]: " + evtId;
    }
}
