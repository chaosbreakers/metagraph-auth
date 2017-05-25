package io.metagraph.auth.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

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
                System.out.println("Password Mode Get Token"  + body +" status code"+response.statusCode() );
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
                System.out.println("Client Mode Get Token"  + body +" status code"+response.statusCode() );
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

    public void testImplicitMode() {
        vertx = Vertx.vertx();
        WebClient client = WebClient.create(vertx);
        HttpRequest<Buffer> request = client.postAbs("http://admin:admin@localhost:8080/oauth/authorize?response_type=token&scope=read%20write&client_id=myClientId&redirect_uri=http://example.com");
        request.send(ar -> {
            if (ar.succeeded()) {
                HttpResponse<Buffer> response = ar.result();
                JsonObject body = response.bodyAsJsonObject();
                System.out.println("Implicit Mode Get Token"  + body +" status code"+response.statusCode() );
            } else {
                System.out.println("Something went wrong " + ar.cause().getMessage());
            }
        });
    }

    public static void main(String[] args) {
        VertxWebTest v = new VertxWebTest();
        //v.testPasswordMode();
        //v.testClientMode();
        v.testImplicitMode();
    }

}
