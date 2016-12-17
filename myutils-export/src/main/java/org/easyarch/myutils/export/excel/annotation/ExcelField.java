package org.easyarch.myutils.export.excel.annotation;

import java.lang.annotation.*;

/**
 * Description :
 * Created by xingtianyu on 16-12-7
 * 下午9:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ExcelField {
    String field() default "";
}
