package net.easysmarthouse.service;

import net.easysmarthouse.service.config.RootConfig;
import net.easysmarthouse.service.context.ProxiedResolverGenericXmlApplicationContext;
import net.easysmarthouse.service.props.AppPropertySource;
import net.easysmarthouse.sheduler.SimpleSchedulerImpl;
import net.easysmarthouse.sheduler.task.RefreshDevicesTask;
import net.easysmarthouse.sheduler.task.TaskProperties;
import net.easysmarthouse.sheduler.thread.CycleTaskThread;
import net.easysmarthouse.sheduler.thread.QueueTaskThread;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MutablePropertySources;

import java.io.File;

/**
 * Created by rusakovich on 24.02.2018.
 */
public class Starter {

    private Starter() {
    }

    private static final String SHEDULER_BEAN_NAME = "scheduler";
    private static final long REFRESH_TASK_DELAY = 200l;

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(RootConfig.class);
        context.registerShutdownHook();

        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        propertySources.addFirst(new AppPropertySource(context.getEnvironment()));

        context.refresh();

        SimpleSchedulerImpl scheduler = (SimpleSchedulerImpl) context.getBean(SHEDULER_BEAN_NAME);
        scheduler.addTask(new RefreshDevicesTask(), new TaskProperties(true, REFRESH_TASK_DELAY));

        CycleTaskThread cycleThread = new CycleTaskThread(true, scheduler);
        QueueTaskThread queueThread = new QueueTaskThread(true, scheduler);

        new Thread(cycleThread).start();

        final Thread execute = new Thread(queueThread);
        execute.start();
        execute.join();
    }

}
