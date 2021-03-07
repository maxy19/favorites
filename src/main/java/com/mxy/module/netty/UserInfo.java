package com.mxy.module.netty;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String username;
    private String age;
    public String getUsername() {
        return username;
    }
    public String getAge() {
        return age;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public UserInfo(String username, String age) {
        super();
        this.username = username;
        this.age = age;
    }
    
    public UserInfo(){
        
    }
    @Override
    public String toString() {
        return "UserInfo [username=" + username + ", age=" + age + "]";
    }
    
    
    
    
}