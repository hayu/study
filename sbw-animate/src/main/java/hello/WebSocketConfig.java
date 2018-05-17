package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
    }

    @Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// "/hello" is the endpoint for websocket client-server negotiation
    	// the setting of the allowed origin can be used to demonstrate websocket with CORS
    	// production code should not use such promiscuous allowance
    	registry.addEndpoint("/hello").setAllowedOrigins("*").withSockJS();
	}
}