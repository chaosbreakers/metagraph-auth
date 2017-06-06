package io.metagraph.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * users实体类定义
 *
 * @author ZhaoPeng
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private int id;
    private String username;
    private String password;
    private boolean enabled;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserEntity that = (UserEntity) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
