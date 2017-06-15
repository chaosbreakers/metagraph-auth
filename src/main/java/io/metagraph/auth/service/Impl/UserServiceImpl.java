package io.metagraph.auth.service.Impl;

import io.metagraph.auth.dao.UserDao;
import io.metagraph.auth.domain.entity.UserEntity;
import io.metagraph.auth.domain.result.MessageResult;
import io.metagraph.auth.enmu.ErrorCode;
import io.metagraph.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZhaoPeng
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public MessageResult findUserById(int id) {
        MessageResult messageResult = new MessageResult();
        try {
            UserEntity userEntity = userDao.findOne(id);
            messageResult.setResult(userEntity);
        } catch (Exception e) {
            messageResult.setCode(ErrorCode.GET_USER_ERROR.getCode());
            messageResult.setMessage(ErrorCode.GET_USER_ERROR.getMessage());
            messageResult.setDescription(ErrorCode.GET_USER_ERROR.getDescription());
        }
        return messageResult;
    }

    @Override
    public MessageResult findByUsername(String username) {
        MessageResult messageResult = new MessageResult();
        try {
            UserEntity userEntity = userDao.findByUsername(username);
            messageResult.setResult(userEntity);
        } catch (Exception e) {
            messageResult.setCode(ErrorCode.GET_USER_ERROR.getCode());
            messageResult.setMessage(ErrorCode.GET_USER_ERROR.getMessage());
            messageResult.setDescription(ErrorCode.GET_USER_ERROR.getDescription());
        }
        return messageResult;
    }

    @Override
    public MessageResult addUser(UserEntity userEntity) {
        MessageResult messageResult = new MessageResult();
        try {
            userEntity = userDao.save(userEntity);
        } catch (Exception e) {
            messageResult.setCode(ErrorCode.ADD_USER_ERROR.getCode());
            messageResult.setMessage(ErrorCode.ADD_USER_ERROR.getMessage());
            messageResult.setDescription(ErrorCode.ADD_USER_ERROR.getDescription());
        }
        return messageResult;
    }


    @Override
    public MessageResult deleteUser(UserEntity userEntity) {
        MessageResult messageResult = new MessageResult();
        try {
            userDao.delete(userEntity);
        } catch (Exception e) {
            messageResult.setCode(ErrorCode.DELETE_USER_ERROR.getCode());
            messageResult.setMessage(ErrorCode.DELETE_USER_ERROR.getMessage());
            messageResult.setDescription(ErrorCode.DELETE_USER_ERROR.getDescription());
        }
        return messageResult;
    }

    @Override
    public MessageResult updateUser(UserEntity userEntity) {
        MessageResult messageResult = new MessageResult();
        try {
            userDao.save(userEntity);
        } catch (Exception e) {
            messageResult.setCode(ErrorCode.UPDATE_USER_ERROR.getCode());
            messageResult.setMessage(ErrorCode.UPDATE_USER_ERROR.getMessage());
            messageResult.setDescription(ErrorCode.UPDATE_USER_ERROR.getDescription());
        }
        return messageResult;
    }
}
