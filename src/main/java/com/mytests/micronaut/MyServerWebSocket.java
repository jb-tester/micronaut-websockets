package com.mytests.micronaut;

import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;

/**
 * *******************************
 * <p>Created by irina on 04.06.2020.</p>
 * <p>Project: micronaut-websockets</p>
 * *******************************
 */
@ServerWebSocket("/ws/${micronaut.application.name}")
public class MyServerWebSocket {
    private WebSocketBroadcaster broadcaster;

    public MyServerWebSocket(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }
    @OnOpen
    public Publisher onOpen(WebSocketSession session){
      broadcaster.broadcast("opened!!!");  
      return session.send("!!!!!! it was opened!");
    }
    @OnMessage
    public Publisher onMessage(String message, WebSocketSession session){
        System.out.println("**********************************");
        System.out.println(message);
        System.out.println("**********************************");
        
      broadcaster.broadcast(message);  
      return session.send("!!!!!!!!!!!!! I've got a message "+message);
    }
    @OnClose
    public void onClose(WebSocketSession session){
       broadcaster.broadcast("closed!"); 
    }
}
