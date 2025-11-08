package com.CloudCrush.metadata_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Test endpoint: client sends message to /app/hello
    @MessageMapping("/hello")
    public void receiveMessage(String message) {
        // Broadcast it to all connected clients at /topic/notifications
        messagingTemplate.convertAndSend("/topic/notifications",
                "Server received: " + message);
    }

    // This can be used from other controllers (like FileController)
    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
