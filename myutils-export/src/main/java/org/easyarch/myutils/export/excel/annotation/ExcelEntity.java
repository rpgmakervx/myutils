package org.easyarch.myutils.export.excel.annotation;

import java.lang.annotation.*;

/**
 * Description : 
 * Created by code4j on 16-12-17
 *  下午3:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ExcelEntity {
    String table() default "";
}