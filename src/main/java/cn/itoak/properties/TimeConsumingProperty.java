package cn.itoak.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author itoak
 */
@EnableConfigurationProperties(TimeConsumingProperty.class)
@ConfigurationProperties("cn.itoak.time.consuming")
public class TimeConsumingProperty {

    private Boolean enabled = Boolean.TRUE;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
