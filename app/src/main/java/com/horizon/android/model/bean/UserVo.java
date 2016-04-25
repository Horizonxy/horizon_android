package com.horizon.android.model.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/21.
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = 4081660037239994816L;

    private String name;
    private String pwd;
    private int age;

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
