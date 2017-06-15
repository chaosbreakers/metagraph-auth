package io.metagraph.auth.controller;

import com.alibaba.fastjson.JSONObject;
import io.metagraph.auth.domain.entity.UserEntity;
import io.metagraph.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZhaoPeng
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addUser(@RequestBody UserEntity userEntity) {
        return JSONObject.toJSONString(userService.addUser(userEntity));
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getUserById(@RequestBody UserEntity userEntity) {
        return JSONObject.toJSONString(userService.findUserById(userEntity.getId()));
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object delete(@RequestBody UserEntity userEntity) {
        return JSONObject.toJSONString(userService.deleteUser(userEntity));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object update(@RequestBody UserEntity userEntity) {
        return JSONObject.toJSONString(userService.updateUser(userEntity));
    }
}
