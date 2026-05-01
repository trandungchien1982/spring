package demo.helloworld.controllers;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@Component
public class SmartLogScheduler {

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> currentTask;

    private int index = 0;
    private int batchCount = 0;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    @PostConstruct
    public void start() {
        startLoggingCycle();
    }

    private void startLoggingCycle() {
        currentTask = scheduler.scheduleAtFixedRate(() -> {

            index++;
            batchCount++;

            String nowStr = LocalDateTime.now().format(FORMATTER);

            System.out.println(
                    "Day la dong logs tu Spring Boot App - "
                            + index
                            + " - "
                            + nowStr
            );

            if (batchCount >= 100) {
                System.out.println(">>> Da log 100 dong, tam dung 5 phut...");

                batchCount = 0;

                // stop current task
                currentTask.cancel(false);

                // schedule resume sau 5 phút
                scheduler.schedule(this::startLoggingCycle,
                        5, TimeUnit.MINUTES);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
    }
}