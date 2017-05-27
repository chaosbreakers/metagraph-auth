package io.metagraph.auth.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * @author ZhaoPeng
 */
public class VertxWebTest {

    private Vertx vertx;

    public void testPasswordMode() {
        vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs("http://localhost:8080/oauth/token?client_id=myClientId&client_secret=myClientSecret&grant_type=password&username=admin&password=admin");
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                System.out.println("Password Mode Get Token" + body + " status code" + response.statusCode());
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

    public void testClientMode() {
        vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs("http://localhost:8080/oauth/token?client_id=myClientId&client_secret=myClientSecret&grant_type=client_credentials&scope=read%20write");
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                System.out.println("Client Mode Get Token" + body + " status code" + response.statusCode());
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

    public void testImplicitMode() {
        vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs("http://admin:admin@localhost:8080/oauth/authorize?response_type=token&scope=read%20write&client_id=myClientId&redirect_uri=http://example.com");
        request.putHeader("Authorization", getHeader());
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                //String location = response.getHeader("Location");
                String body = response.bodyAsString();
                System.out.println("Implicit Mode Get Token" + body + " status code" + response.statusCode()+" Location=");
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

    public void testAuthorizationCode() {
        vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs("http://admin:admin@localhost:8080/oauth/authorize?client_id=myClientId&client_secret=myClientSecret&redirect_uri=http://example.com&response_type=code&scope=read%20write");
        request.putHeader("Authorization", getHeader());
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                String body = response.bodyAsString();
                System.out.println("Authorization Code Mode Get Token" + body + " status code" + response.statusCode());
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

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

    public static void main(String[] args) {
        VertxWebTest v = new VertxWebTest();
        //v.testPasswordMode();
        //v.testClientMode();
        //v.testImplicitMode();
        v.testAuthorizationCode();
    }

}
