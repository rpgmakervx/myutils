package org.easyarch.myutils.test.orm.bean;

/**
 * Description :
 * Created by xingtianyu on 17-1-13
 * 上午1:50
 * description:
 */

public class SqlBean {
    private String columnName;

    private Integer params;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getParams() {
        return params;
    }

    public void setParams(Integer params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SqlBean{" +
                "columnName='" + columnName + '\'' +
                ", params=" + params +
                '}';
    }
}
