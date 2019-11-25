//package com.nc.backend.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="users")
//public class User {
//    @Id
//    private int id;
//
//    @Column
//    private String nickname;
//
//    @Column
//    private String name;
//
//    @Column
//    private String surname;
//
//    @Column
//    private String role;
//
//    @Column
//    private String profile_description;
//
//    @Column
//    private String password;
//
//    @Column
//    @Enumerated(EnumType.STRING)
//    private UserStatus status;
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getProfile_description() {
//        return profile_description;
//    }
//
//    public void setProfile_description(String profile_description) {
//        this.profile_description = profile_description;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setStatus(UserStatus st) {
//        this.status = st;
//    }
//
//    public UserStatus getStatus() {
//        return this. status;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//}
