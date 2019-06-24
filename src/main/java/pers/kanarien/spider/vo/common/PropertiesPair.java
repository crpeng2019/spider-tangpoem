package com.seewo.spider.vo.common;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertiesPair {
    String key() default "";
}
