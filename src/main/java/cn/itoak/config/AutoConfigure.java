package cn.itoak.config;

import cn.itoak.aspect.TimeConsumingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author itoak
 */
@Configuration
@EnableAspectJAutoProxy
public class AutoConfigure {

    @Bean
    @ConditionalOnProperty(prefix = "cn.itoak.time.consuming", value = "enabled", havingValue = "true")
    public TimeConsumingAspect timeConsumingAspect(){
        return new TimeConsumingAspect();
    }

}
