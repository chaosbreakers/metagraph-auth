package io.metagraph.auth.dao;

import io.metagraph.auth.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * users表DAO接口
 * @author ZhaoPeng
 */

public interface UserDao extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
}
