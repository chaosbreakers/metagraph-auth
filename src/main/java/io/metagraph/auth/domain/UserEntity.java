package io.metagraph.auth.domain;

import javax.persistence.*;


/**
 * users实体类定义
 *
 * @author ZhaoPeng
 */
@Entity
@Table(name = "users", schema = "", catalog = "")
public class UserEntity {

    @Id
    private int id;

    private String username;

    private String password;

    private int enabled;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
