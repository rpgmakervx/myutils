package org.easyarch.myutils.algorithm.sort.tree;

/**
 * Created by xingtianyu on 2018/3/27.
 */
public class User implements Comparable<User>{
    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        if (age == null||o == null){
            return 0;
        }
        if (o.age.equals(age)){
            return 0;
        }
        if (age > o.age){
            return 1;
        }
        if (o.age > age){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
