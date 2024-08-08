package com.ghostface.dev;


import com.ghostface.dev.application.JChatRoom;

public class ClientApplication {
    public static void main(String[] args) {
        JChatRoom room = new JChatRoom();
        room.join();
    }
}
