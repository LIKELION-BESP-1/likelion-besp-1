package com.besp.likebesp1.Chat.controller;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import com.besp.likebesp1.Chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatMessageService chatMessageService;

	@Autowired
	public ChatController(SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService) {
		this.messagingTemplate = messagingTemplate;
		this.chatMessageService = chatMessageService;
	}

	@MessageMapping("/{roomId}")
	public void send_message(ChatMessageDto message, @DestinationVariable Long roomId) {
		chatMessageService.saveChatMessage(message);

		messagingTemplate.convertAndSend("/room/" + roomId, message);
	}

}
