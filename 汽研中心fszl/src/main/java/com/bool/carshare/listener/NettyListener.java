package com.bool.carshare.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.bool.carshare.service.impl.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;


@WebListener
public class NettyListener implements ServletContextListener{

//    @Autowired
//	private INettyService nettyService;
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new Thread(){
			@Override
			public void run() {
				try {
					NettyServer.start(51111);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	
}
