package com.besp.likebesp1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration 
@EnableWebSocketMessageBroker  //소켓을 사용하겠다. 
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	//기본설정을 담당한다. 
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/room");
		registry.setApplicationDestinationPrefixes("/send"); //html문서의 자바스크립트
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-stomp").withSockJS();
	}

}





