package com.besp.likebesp1.Chat.controller;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import com.besp.likebesp1.Chat.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
		chatMessageService.saveChatMessage(message, roomId);

		messagingTemplate.convertAndSend("/room/" + roomId, message);
	}

	@GetMapping("/{roomId}/messages")
	@ResponseBody
	public ResponseEntity<List<ChatMessageDto>> getChatMessages(@PathVariable Long roomId) {
		List<ChatMessageDto> chatMessages = chatMessageService.getChatMessagesByRoomId(roomId);
		return ResponseEntity.ok(chatMessages);
	}

}
