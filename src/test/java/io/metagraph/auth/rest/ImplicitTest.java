package io.metagraph.auth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhaoPeng
 */
public class ImplicitTest extends OAuth2Test {
    @Test
    public void testImplicit() throws IOException {
        unauthorizedRequest();

        String authUrl = authUrlPrefix + "/oauth/authorize?response_type=token&client_id={clientId}&redirect_uri={redirectUrl}";
        ResponseEntity<String> response = new TestRestTemplate(username, password).postForEntity(authUrl, null, String.class, clientId, redirectUrl);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        String cookieValue = response.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", cookieValue);
        authUrl = authUrlPrefix + "/oauth/authorize?user_oauth_approval=true&scope.read=true";
        response = new TestRestTemplate().postForEntity(authUrl, new HttpEntity<>(headers), String.class);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        String location = response.getHeaders().get("Location").get(0).replace("#", "?");
        response = new TestRestTemplate().getForEntity(location, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("secure", new ObjectMapper().readValue(response.getBody(), HashMap.class).get("content"));
    }
}
