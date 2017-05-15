package org.bin.socket.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * <p>Title:RequestListener</p>
 * <p>Description:</p>
 * @author binH
 * @date 2017年5月5日 下午2:06:04
 */
@Component
public class RequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		 ((HttpServletRequest) sre.getServletRequest()).getSession();
	}

}
