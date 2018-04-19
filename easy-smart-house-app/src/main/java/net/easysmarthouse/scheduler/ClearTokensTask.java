package net.easysmarthouse.scheduler;

import net.easysmarthouse.shared.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ClearTokensTask {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "${tokens.cron.expression}")
    public void execute() {
        Date now = Date.from(Instant.now());
        userService.deleteExpiredTokens(now);
    }

}
