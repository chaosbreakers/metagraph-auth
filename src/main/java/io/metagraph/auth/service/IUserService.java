package io.metagraph.auth.service;

import io.metagraph.auth.domain.entity.UserEntity;
import io.metagraph.auth.domain.result.MessageResult;

/**
 * @author ZhaoPeng
 */
public interface IUserService {

    MessageResult findUserById(int id);

    MessageResult findByUsername(String username);

    MessageResult addUser(UserEntity userEntity);

    MessageResult deleteUser(UserEntity userEntity);

    MessageResult updateUser(UserEntity userEntity);
}
