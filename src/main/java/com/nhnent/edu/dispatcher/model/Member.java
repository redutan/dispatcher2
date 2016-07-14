package com.nhnent.edu.dispatcher.model;

public class Member {
    private String id;
    private String password;


    public Member() {
        // nothing
    }

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }


    public static Member newInstance(String id, String password) {
        return new Member(id, password);
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
