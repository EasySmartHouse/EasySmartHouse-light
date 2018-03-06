package net.easysmarthouse.service.config;

import net.easysmarthouse.network.NetworkManager;
import net.easysmarthouse.sheduler.Scheduler;
import net.easysmarthouse.sheduler.SimpleSchedulerImpl;
import net.easysmarthouse.sheduler.task.TaskProcessor;
import net.easysmarthouse.sheduler.task.TaskProcessorImpl;
import net.easysmarthouse.sheduler.task.TimeManager;
import net.easysmarthouse.sheduler.task.TimeManagerImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(NetworkBaseConfig.class)
public class SchedulerConfig {

    @Bean
    public TimeManager timeManager() {
        return new TimeManagerImpl();
    }

    @Bean
    public TaskProcessor taskProcessor(TimeManager timeManager) {
        TaskProcessorImpl taskProcessor = new TaskProcessorImpl();
        taskProcessor.setTimeManager(timeManager);
        return taskProcessor;
    }

    @Bean(name = "scheduler")
    public Scheduler scheduler(@Qualifier("networkManagersHub") NetworkManager networkManager,
                               TaskProcessor taskProcessor) {
        SimpleSchedulerImpl scheduler = new SimpleSchedulerImpl();
        scheduler.setTaskProcessor(taskProcessor);
        scheduler.setNetworkManager(networkManager);
        return scheduler;
    }

}
