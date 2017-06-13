package io.metagraph.auth.rest;

import io.metagraph.auth.dao.UserDao;
import io.metagraph.auth.domain.UserEntity;
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
    UserDao userDao;

    @Test
    public void testAuthorizationCode() throws IOException, URISyntaxException {
        UserEntity user = userDao.findByUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }
}
