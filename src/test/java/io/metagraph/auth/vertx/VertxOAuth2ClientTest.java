package io.metagraph.auth.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.AccessToken;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.codec.binary.Base64;
import java.nio.charset.Charset;

/**
 * @author ZhaoPeng
 */
public class VertxOAuth2ClientTest {

    /**
     * 构造Basic Auth认证头信息
     *
     * @returnse.getHeader("Location");
     */
    private String getHeader() {
        String auth = "admin" + ":" + "admin";
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }

    public void testAuthorizationCodeMode() {
        Vertx vertx = Vertx.vertx();
        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.AUTH_CODE, new OAuth2ClientOptions()
                .setClientID("myClientId")
                .setClientSecret("myClientSecret")
                .setSite("http://localhost:8080")
                .setTokenPath("/oauth/access_token")
                .setAuthorizationPath("/oauth/authorize")
        );

        // when there is a need to access a protected resource or call a protected method,
        // call the authZ url for a challenge

        String authorization_uri = oauth2.authorizeURL(new JsonObject()
                .put("redirect_uri", "http://localhost:8080/secure")
                .put("scope", "read"));

        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs(authorization_uri);
        request.putHeader("Authorization", getHeader());
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                String body = response.bodyAsString();
                System.out.println("Authorization Code Mode Get Token" + body + " status code" + response.statusCode());

                //.put("state", "3(#0/!~"));
                // when working with web application use the above string as a redirect url
                // in this case GitHub will call you back in the callback uri one should now complete the handshake as:
                /*String code = "xxxxxxxxxxxxxxxxxxxxxxxx"; // the code is provided as a url parameter by github callback call
                oauth2.getToken(new JsonObject().put("code", code).put("redirect_uri", "http://localhost:8080/secure"), res -> {
                    if (res.failed()) {
                        // error, the code provided is not valid
                    } else {
                        // save the token and continue...
                        System.out.println("AuthorizationCode Mode Get Token Info= " + res.result().toString());
                    }
                });*/

            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });


    }

    public void testClientCredentialsMode() {
        Vertx vertx = Vertx.vertx();
        OAuth2ClientOptions credentials = new OAuth2ClientOptions()
                .setClientID("myClientId")
                .setClientSecret("myClientSecret")
                .setSite("http://localhost:8080");
        // Initialize the OAuth2 Library
        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.CLIENT, credentials);
        JsonObject tokenConfig = new JsonObject();
        // Callbacks
        // Save the access token
        oauth2.getToken(tokenConfig, res -> {
            if (res.failed()) {
                System.err.println("Access Token Error: " + res.cause().getMessage());
            } else {
                // Get the access token object (the authorization code is given from the previous step).
                AccessToken token = res.result();
                System.out.println("Credentials Mode Get Token Info= " + token.principal().toString());
                oauth2.api(HttpMethod.GET, "/secure", new JsonObject().put("access_token", token.principal().getString("access_token")), res2 -> {
                    // the user object should be returned here...
                    System.out.println("Secure Info= " + res2.result().toString());
                });
            }
        });
    }

    public void testPasswordMode() {
        Vertx vertx = Vertx.vertx();
        OAuth2ClientOptions credentials = new OAuth2ClientOptions()
                .setClientID("myClientId")
                .setClientSecret("myClientSecret")
                .setSite("http://localhost:8080");
        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.PASSWORD, credentials);
        JsonObject tokenConfig = new JsonObject()
                .put("username", "admin")
                .put("password", "admin");
        // Callbacks
        // Save the access token
        oauth2.getToken(tokenConfig, res -> {
            if (res.failed()) {
                System.err.println("Access Token Error: " + res.cause().getMessage());
            } else {
                // Get the access token object (the authorization code is given from the previous step).
                AccessToken token = res.result();
                System.out.println("Password Mode Get Token Info= " + token.principal().toString());
                oauth2.api(HttpMethod.GET, "/verifyToken", new JsonObject().put("access_token", token.principal().getString("access_token")), res2 -> {
                    // the user object should be returned here...
                    System.out.println("verifyToken Info= " + res2.result().toString());
                    //刷新token
                    if (token.expired()) {
                        // Callbacks
                        token.refresh(res3 -> {
                            if (res3.succeeded()) {
//                            /System.out.println("Refresh Token success= " + res3.result().toString());
                            } else {
                                //System.out.println("Refresh Token fail= " + res3.result().toString());
                            }
                        });
                    }
                });
            }
        });
    }


    public static void main(String[] args) {
        VertxOAuth2ClientTest vertxOAuth2ClientTest = new VertxOAuth2ClientTest();
        vertxOAuth2ClientTest.testPasswordMode();
        //vertxOAuth2ClientTest.testClientCredentialsMode();
        //vertxOAuth2ClientTest.testAuthorizationCodeMode();
    }
}
