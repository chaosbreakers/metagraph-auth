package io.metagraph.auth.rest;

import io.metagraph.auth.MetagraphAuth;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author ZhaoPeng
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MetagraphAuth.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {
}
