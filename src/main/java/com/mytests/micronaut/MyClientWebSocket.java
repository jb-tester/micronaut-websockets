package com.mytests.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.ClientWebSocket;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * *******************************
 * <p>Created by irina on 04.06.2020.</p>
 * <p>Project: micronaut-websockets</p>
 * *******************************
 */
@ClientWebSocket("/ws/${micronaut.application.name}")
public abstract class MyClientWebSocket implements AutoCloseable {
    private WebSocketSession session;
    private HttpRequest request;
    
    private Collection<String> replies = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void onOpen( WebSocketSession session, HttpRequest request) {
       
        this.session = session;
        this.request = request;
    }
    @OnMessage
    public void onMessage(
            String message) {
        System.out.println("Client received message = " + message);
        replies.add(message);
    }
    public abstract void send(String message);
    
    public WebSocketSession getSession() {
        return session;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public Collection<String> getReplies() {
        return replies;
    }
}
