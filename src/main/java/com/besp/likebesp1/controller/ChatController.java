package com.besp.likebesp1.controller;

import com.besp.likebesp1.entity.ChatMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

	@MessageMapping("/{roomId}")
	@SendTo("/room/{roomId}")
	public ChatMessageDto send_message(ChatMessageDto message) {
		return message;
	}
}
