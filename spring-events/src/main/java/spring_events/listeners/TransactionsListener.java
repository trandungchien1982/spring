package spring_events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import spring_events.events.MyTransactionEvent;

@Component
public class TransactionsListener {

    Logger log = LoggerFactory.getLogger(getClass());

    // Try to listen the event before commit a transaction
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    void handleBeforeCommitEvent(MyTransactionEvent event) {
        log.info("[TransactionsListener] :: before commit: " + event + ", currentThread: " + Thread.currentThread());

        try {
            Thread.sleep(500);
        } catch (Exception ignore) {}
    }

    // Try to listen the event before commit a transaction
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void handleAfterCommitEvent(MyTransactionEvent event) {
        log.info("[TransactionsListener] :: after commit: " + event + ", currentThread: " + Thread.currentThread());

        try {
            Thread.sleep(500);
        } catch (Exception ignore) {}
    }

    // Try to listen the event after complete a transaction
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    void handleAfterCompletionEvent(MyTransactionEvent event) {
        log.info("[TransactionsListener] :: after completion: " + event + ", currentThread: " + Thread.currentThread());

        try {
            Thread.sleep(500);
        } catch (Exception ignore) {}
    }

    // Try to listen the event before commit a transaction
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    void handleAfterRollbackEvent(MyTransactionEvent event) {
        log.info("[TransactionsListener] :: after Rollback : " + event + ", currentThread: " + Thread.currentThread());

        try {
            Thread.sleep(500);
        } catch (Exception ignore) {}
    }

}
