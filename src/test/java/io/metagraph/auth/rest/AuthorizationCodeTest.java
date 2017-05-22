package io.metagraph.auth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author ZhaoPeng
 */
public class AuthorizationCodeTest extends OAuth2Test {
    @Test
    public void testAuthorizationCode() throws IOException, URISyntaxException {
        String authUrl = authBasicUrlPrefix + "/oauth/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUrl + "&scope=read write";
        ResponseEntity<String> response = new TestRestTemplate(username, password).postForEntity(authUrl, null, String.class);
        HttpHeaders headers = new HttpHeaders();
        //assertEquals(response.getBody(), HttpStatus.OK, response.getStatusCode());

        List<String> setCookie = response.getHeaders().get("Set-Cookie");
        String jSessionIdCookie = setCookie.get(0);
        String cookieValue = jSessionIdCookie.split(";")[0];
        headers.add("Cookie", cookieValue);
        authUrl = authUrlPrefix + "/oauth/authorize?user_oauth_approval=true&scope.read=true";
        //response = new TestRestTemplate().postForEntity(authUrl, new HttpEntity<>(headers), String.class);
        //assertEquals(response.getBody(), HttpStatus.FOUND, response.getStatusCode());

        String query = new URI(response.getHeaders().get("Location").get(0)).getQuery();
        String tokenUrl = authUrlPrefix + "/oauth/token?grant_type=authorization_code&redirect_uri={redirectUrl}&" + query;
        response = new TestRestTemplate(clientId, clientSecret).postForEntity(tokenUrl, new HttpEntity<>(new HttpHeaders()), String.class, redirectUrl);
        assertEquals(response.getBody(), HttpStatus.OK, response.getStatusCode());

        HashMap map = new ObjectMapper().readValue(response.getBody(), HashMap.class);
        String accessToken = (String) map.get("access_token");
        String refreshToken = (String) map.get("refresh_token");
        System.out.println("Token Info:" + map.toString());

        /*headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        response = new TestRestTemplate().exchange(redirectUrl, HttpMethod.GET, new HttpEntity<>(null, headers), String.class);
        assertEquals(response.getBody(), HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(), "secure", new ObjectMapper().readValue(response.getBody(), HashMap.class).get("content"));

        refreshToken(refreshToken);*/
    }
}
