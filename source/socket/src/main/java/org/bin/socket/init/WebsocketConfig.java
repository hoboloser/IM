//package org.bin.socket.init;
//
//import org.bin.socket.listener.RequestListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//
//@Configuration
//public class WebsocketConfig {
//	@Bean  
//    public ServerEndpointExporter serverEndpointExporter (){  
//        return new ServerEndpointExporter();  
//    }  
//	
//	@Autowired
//	private RequestListener requestListener;
//	
//	@Bean
//    public ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean() {
//        ServletListenerRegistrationBean<RequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
//        servletListenerRegistrationBean.setListener(requestListener);
//        return servletListenerRegistrationBean;
//    }
//}
