package org.easyarch.myutils.orm.annotation;

/**
 * Description :
 * Created by code4j on 16-12-25
 * 下午9:34
 */

public @interface Table {

    String tableName() default "";
}
