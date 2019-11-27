package cn.itoak.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author itoak
 */
//@Component
//@EnableConfigurationProperties(TimeConsumingProperty.class)
//@ConfigurationProperties("cn.itoak.slow.query")
public class SlowQueryProperty {

    private static final String EXECUTION_TEMPLATE = "execution(* %s..*.*(..))";
    private static final String EXECUTION_SEPARATOR = " || ";
    private static final String PACKAGE_SEPARATOR = ",";

    /**
     * 监控包
     */
    private String monitorPackage;

    /**
     * 慢查询阈值，超过阈值则认为是慢查询
     */
    private Long threshold = 3000L;

    /**
     * 慢查询开关
     */
    private Boolean enabled = false;

    /**
     * 获取监控包名
     *
     * @return 包名的切点
     */
    public String obtainMonitorPackage() {
        if (!"".equals(monitorPackage)){
            if (monitorPackage.contains(PACKAGE_SEPARATOR)){
                String[] packages = monitorPackage.split(PACKAGE_SEPARATOR);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < packages.length; i++) {
                    stringBuilder.append(String.format(EXECUTION_TEMPLATE, packages[i]));
                    if (i != packages.length - 1){
                        stringBuilder.append(EXECUTION_SEPARATOR);
                    }
                }
                return stringBuilder.toString();
            } else {
                return String.format(EXECUTION_TEMPLATE, monitorPackage.trim());
            }
        }
        return "";
    }

    public String getMonitorPackage() {
        return monitorPackage;
    }

    public void setMonitorPackage(String monitorPackage) {
        this.monitorPackage = monitorPackage;
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
