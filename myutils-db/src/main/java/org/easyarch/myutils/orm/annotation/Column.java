package org.easyarch.myutils.orm.annotation;

import org.easyarch.myutils.orm.mapper.JDBCType;

/**
 * Description :
 * Created by code4j on 16-12-25
 * 下午10:00
 */

public @interface Column {
    String name() default "";
    JDBCType type() default JDBCType.VARCHAR;
}
