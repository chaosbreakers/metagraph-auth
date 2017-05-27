package io.metagraph.auth.domain;

import javax.persistence.*;


//@Entity
//@Table(name = "users" ,schema = "metagraph" ,catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String password;
    private int enable;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    @Basic
    @Column(name = "enable")
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer age) {
        this.enable = age;
    }


    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
