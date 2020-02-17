package com.framework.security;

import java.lang.annotation.*;

/**
 * 
 * @date 2016年10月20日
 *
 * @author LiQiang
 *
 * @description 忽略安全性检查
 * 
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {
}
