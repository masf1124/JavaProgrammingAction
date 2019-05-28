package com.algorithm.annotation;

import java.lang.annotation.*;

/**
 * @program: com.algorithm.annotation
 * @author: mashifei
 * @create: 2019-05-28-19
 * 参数级注解，用于注解商品ID等基本参数类型
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {
}
