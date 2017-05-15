package org.bin.socket;

import org.bin.socket.controller.bootsocket.SocketHandler;
import org.bin.socket.controller.bootsocket.SocketHandshakeInterceptor;
import org.bin.socket.init.BeanFactory;
import org.bin.socket.service.MemberService;
import org.bin.socket.service.MessageService;
import org.bin.socket.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

@SpringBootApplication
@EnableWebSocket
public class ApplicationStart extends SpringBootServletInitializer implements WebSocketConfigurer{
	
	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(ApplicationStart.class, args);
		BeanFactory bean = new BeanFactory();
		bean.setApplicationContext(cac);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		SpringApplicationBuilder s =  builder.sources(ApplicationStart.class);
		BeanFactory bean = new BeanFactory();
		bean.setApplicationContext(s.context());
		return s;
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler(), "/imserver").addInterceptors(socketHandshakeInterceptor()).withSockJS();
	}
	@Autowired
	private UserGroupService userGroupService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MessageService messageService;
	
	@Bean
	public WebSocketHandler socketHandler(){
		return new SocketHandler(userGroupService,memberService,messageService);
	}
	
	@Bean
	public HandshakeInterceptor socketHandshakeInterceptor() {
		return new SocketHandshakeInterceptor();
	}
	
}
