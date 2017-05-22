package io.metagraph.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ClientCredentialTest extends OAuth2Test {
    @Test
    public void testClientCredential() throws IOException {
        unauthorizedRequest();

        String tokenUrl = authUrlPrefix + "/oauth/token?grant_type=client_credentials";
        ResponseEntity<String> response = new TestRestTemplate(clientId, clientSecret).postForEntity(tokenUrl, null, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        HashMap map = new ObjectMapper().readValue(response.getBody(), HashMap.class);
        String accessToken = (String) map.get("access_token");
        String refreshToken = (String) map.get("refresh_token");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        response = new TestRestTemplate().exchange(redirectUrl, HttpMethod.GET, new HttpEntity<>(null, headers), String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("secure", new ObjectMapper().readValue(response.getBody(), HashMap.class).get("content"));

        refreshToken(refreshToken);
    }
}
