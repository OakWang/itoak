package cn.itoak.annotation;

import java.lang.annotation.*;

/**
 * @author itoak
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TimeConsuming {

}
