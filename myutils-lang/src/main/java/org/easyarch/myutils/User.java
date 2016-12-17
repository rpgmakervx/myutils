package org.easyarch.myutils;/**
 * Description : 
 * Created by YangZH on 16-11-3
 *  上午12:44
 */

/**
 * Description :
 * Created by code4j on 16-11-3
 * 上午12:44
 */

public class User {

    private String username;
    private int age;

    public User(){}

    public User(String username, int age) {
        this.username = username;
        this.age = age;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
