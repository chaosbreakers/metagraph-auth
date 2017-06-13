package io.metagraph.auth.rest;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhaoPeng
 */
public class OAuth2Test extends BaseTest {

    @Value("${server.auth.basic.urlPrefix}")
    protected String authBasicUrlPrefix;

    @Value("${server.auth.urlPrefix}")
    protected String authUrlPrefix;

    @Value("${server.auth.redirectUrl}")
    protected String redirectUrl;

    @Value("${server.auth.resourceUrl}")
    protected String resourceUrl;

    @Value("${server.auth.username}")
    protected String username;

    @Value("${server.auth.password}")
    protected String password;

    @Value("${server.auth.clientId}")
    protected String clientId;

    @Value("${server.auth.clientSecret}")
    protected String clientSecret;


    @Before
    public void setUp() {
    }

    protected void unauthorizedRequest() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity(redirectUrl, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    protected void refreshToken(String refreshToken) {
        String tokenUrl = authUrlPrefix + "/oauth/token?grant_type=refresh_token&refresh_token={refreshToken}";
        ResponseEntity<String> response = new TestRestTemplate(clientId, clientSecret).postForEntity(tokenUrl, null, String.class, refreshToken);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
