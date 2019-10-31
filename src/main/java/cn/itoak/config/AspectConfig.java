package cn.itoak.config;

import cn.itoak.aspect.TimeConsumingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author itoak
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public TimeConsumingAspect timeConsumingAspect(){
        return new TimeConsumingAspect();
    }
}
