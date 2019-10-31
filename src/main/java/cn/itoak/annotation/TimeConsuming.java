package cn.itoak.annotation;

import java.lang.annotation.*;

/**
 * @author itoak
 * @date 2019-10-29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TimeConsuming {

}
