package io.metagraph.auth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhaoPeng
 */
public class ClientCredentialTest extends OAuth2Test {
    @Test
    public void testClientCredential() throws IOException {
        String tokenUrl = authUrlPrefix + "/oauth/token?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=client_credentials";
        ResponseEntity<String> response = new TestRestTemplate().postForEntity(tokenUrl, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HashMap map = new ObjectMapper().readValue(response.getBody(), HashMap.class);
        String accessToken = (String) map.get("access_token");
        String refreshToken = (String) map.get("refresh_token");
        System.out.println("Token Info:" + map.toString());
    }
}
