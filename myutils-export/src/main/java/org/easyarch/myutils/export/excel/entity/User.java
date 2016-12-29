package org.easyarch.myutils.export.excel.entity;

import org.easyarch.myutils.export.excel.annotation.ExcelEntity;
import org.easyarch.myutils.export.excel.annotation.ExcelField;

import java.util.Date;

/**
 * Description :
 * Created by xingtianyu on 16-12-17
 * 下午6:19
 */

@ExcelEntity(table = "用户表")
public class User {

    @ExcelField(field = "用户名")
    private String username;

    @ExcelField(field = "年龄")
    private int age;
    @ExcelField(field = "年龄")
    private String page;
    @ExcelField(field = "年龄")
    private String num;
    @ExcelField(field = "年龄")
    private String year;

    @ExcelField(field = "创建时间")
    private Date createDate;
    public User(String username, int age, String page, String num, String year, Date createDate) {
        this.username = username;
        this.age = age;
        this.page = page;
        this.num = num;
        this.year = year;
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
