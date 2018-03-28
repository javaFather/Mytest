package common.annotation;

import java.lang.annotation.*;

/**
 * @author: wangzx
 * @Date: 2018-03-28-15:07
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XlsHeader {
    String value() default "";

}
