package io.metagraph.auth.util;

import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;

/**
 * @author ZhaoPeng
 */
public class AuthorizationUtil {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private AuthorizationUtil() {
    }

    public static HttpHeaders basic(String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = Base64Utils.encodeToString((username + ":" + password).getBytes(UTF_8));
        httpHeaders.add("Authorization", "Basic " + token);
        return httpHeaders;
    }

    public static HttpHeaders bearer(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        return httpHeaders;
    }
}
