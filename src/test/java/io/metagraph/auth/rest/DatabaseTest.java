package io.metagraph.auth.rest;

import io.metagraph.auth.domain.entity.UserEntity;
import io.metagraph.auth.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhaoPeng
 */
public class DatabaseTest extends OAuth2Test {

    @Autowired
    IUserService iUserService;

    @Test
    public void testUserRest() throws IOException, URISyntaxException {
        UserEntity user = iUserService.findByUsername("admin");
        assertEquals(user.getUsername(), "admin");

        UserEntity user1 = iUserService.findUserById(18);
        assertEquals(user.getUsername(), "admin");
    }
}
