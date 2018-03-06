package net.easysmarthouse.service.config;

import net.easysmarthouse.service.logging.ActuatorModuleLoggingInterceptor;
import net.easysmarthouse.service.logging.SignalingModuleLoggingInterceptor;
import net.easysmarthouse.service.logging.TriggerModuleLoggingInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoggingConfig {

    @Bean
    public AspectJExpressionPointcutAdvisor signalingPointcutAdvisor(){
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution (* net.easysmarthouse.provider.device.alarm.SignalingModule.setEnabled(..)))");
        advisor.setAdvice(new SignalingModuleLoggingInterceptor());
        return advisor;
    }

    @Bean
    public AspectJExpressionPointcutAdvisor actuatorsPointcutAdvisor(){
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution (* net.easysmarthouse.provider.device.actuator.ActuatorsModule.changeState(..)))");
        advisor.setAdvice(new ActuatorModuleLoggingInterceptor());
        return advisor;
    }

    @Bean
    public AspectJExpressionPointcutAdvisor triggersPointcutAdvisor(){
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution (* net.easysmarthouse.provider.device.trigger.TriggerModule.setEnabled(..)))");
        advisor.setAdvice(new TriggerModuleLoggingInterceptor());
        return advisor;
    }

}
