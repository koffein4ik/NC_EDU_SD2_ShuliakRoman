package com.nc.backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "photosquare")
public class UserEntity {
    private int id;
    private String nickname;
    private String name;
    private String surname;
    private String role;
    private String profileDescription;
    private String password;
    private String email;
    private UserStatus status;
    private Set<UserEntity> subscribers = new HashSet<>();
    //private Set<UserEntity> subscribedTo;

//    @OneToMany(mappedBy = "subscriber")
//    public Set<UserEntity> getSubscribers() {
//        return subscribers;
//    }
//
//    public void setSubscribers(Set<UserEntity> subscribers) {
//        this.subscribers = subscribers;
//    }

//    @OneToMany(mappedBy = "subscribedTo")
//    public Set<UserEntity> getSubscribedTo() {
//        return subscribedTo;
//    }
//
//    public void setSubscribedTo(Set<UserEntity> subscribedTo) {
//        this.subscribedTo = subscribedTo;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "profile_description")
    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(role, that.role) &&
                Objects.equals(profileDescription, that.profileDescription) &&
                Objects.equals(password, that.password) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, name, surname, role, profileDescription, password, status);
    }
}
