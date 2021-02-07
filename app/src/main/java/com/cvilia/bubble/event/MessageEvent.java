package com.cvilia.bubble.event;

/**
 * author: lzy
 * date: 2/7/21
 * describe：EventBus
 */
public class MessageEvent {

    public final String event;

    public static MessageEvent getInstance(String event){
        return new MessageEvent(event);
    }

    private MessageEvent(String event){
        this.event = event;
    }

}
