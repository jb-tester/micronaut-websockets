package com.mytests.micronaut;

import io.micronaut.http.annotation.PathVariable;
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
@ServerWebSocket("/ws2/{smth}")
public class MyServerWebSocketWithPathVars {
    private WebSocketBroadcaster broadcaster;

    public MyServerWebSocketWithPathVars(WebSocketBroadcaster broadcaster) {
        this.broadcaster = broadcaster;
    }
    @OnOpen
    public Publisher onOpen(WebSocketSession session, @PathVariable("smth") String pvar1){
      broadcaster.broadcast("pathvars socket opened for "+pvar1);  
      return session.send("pathvars socket was opened for "+pvar1);
    }
    @OnMessage
    public Publisher onMessage(String message, @PathVariable("smth") String pvar1, WebSocketSession session){
        System.out.println("**********************************");
        System.out.println(message+" "+pvar1);
        System.out.println("**********************************");
        
      broadcaster.broadcast(message);  
      return session.send("pathvars socket got a message "+message);
    }
    @OnClose
    public void onClose(WebSocketSession session, @PathVariable("smth") String pvar1){
       broadcaster.broadcast("pathvars socket was closed!!!");
       session.send("pathvars socket was closed") ;
    }
}
