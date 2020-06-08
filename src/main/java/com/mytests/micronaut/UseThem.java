package com.mytests.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.websocket.RxWebSocketClient;

import javax.inject.Inject;
import java.util.Collection;
import java.util.HashMap;

/**
 * *******************************
 * <p>Created by irina on 04.06.2020.</p>
 * <p>Project: micronaut-websockets</p>
 * *******************************
 */
@Controller
public class UseThem {
    
    @Inject
    @Client("http://localhost:8080")
    RxWebSocketClient webSocketClient;
    
    @Get("/sendToPlain")
    public Collection<String> sendToPlain(){
        //MyClientWebSocket myClient = webSocketClient.connect(MyClientWebSocket.class, new HashMap<>()).blockingFirst(); 
        MyClientWebSocket myClient = webSocketClient.connect(MyClientWebSocket.class, "/ws/micronautWebsockets").blockingFirst(); 
    myClient.send("HELLO!!!!");  
    return myClient.getReplies();
    }
    @Get("/withVars")
    public Collection<String> sendWithPathVars(){
         
        MyClientWebSocketWithPathvars myClient = webSocketClient.connect(MyClientWebSocketWithPathvars.class, "/ws2/foo").blockingFirst();
        myClient.send("HELLO AGAIN");
        return myClient.getReplies();
    }
    
}
