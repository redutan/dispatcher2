package com.nhnent.edu.dispatcher.model;

// TODO: 2. Member에 MemberType 필드 추가
public class Member {
    private String id;
    private String password;
    private MemberType type;


    public Member() {
        // nothing
    }

    public Member(String id, String password, MemberType type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }


    public static Member newInstance(String id, String password, MemberType type) {
        return new Member(id, password, type);
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

    public MemberType getType() {
        return type;
    }
    public void setType(MemberType type) {
        this.type = type;
    }

}
