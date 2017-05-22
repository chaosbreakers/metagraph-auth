package io.metagraph.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class OAuth2Test extends  BaseTest{

    @Value("${server.auth.urlPrefix}")
    protected String authUrlPrefix;
    //@Value("${server.resource.urlPrefix}")
    //protected String resourceUrlPrefix;

    protected String redirectUrl;

    protected String username = "admin";
    protected String password = "admin";
    protected String clientId = "myClientId";
    protected String clientSecret = "myClientSecret";


    /*@Before
    public void setUp() {
        redirectUrl = resourceUrlPrefix + "/secure";
    }*/

    protected void unauthorizedRequest() {
        /*ResponseEntity<String> response = new TestRestTemplate().getForEntity(redirectUrl, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());*/
    }

    protected void refreshToken(String refreshToken) {
        String tokenUrl = authUrlPrefix + "/oauth/token?grant_type=refresh_token&refresh_token={refreshToken}";
        ResponseEntity<String> response = new TestRestTemplate(clientId, clientSecret).postForEntity(tokenUrl, null, String.class, refreshToken);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
