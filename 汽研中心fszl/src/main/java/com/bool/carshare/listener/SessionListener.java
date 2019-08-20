/**
 * 
 */
package com.bool.carshare.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.bool.carshare.util.SessionUtil;

/**
 * SessionListener
 * @author wangw
 */
@WebListener
public class SessionListener implements HttpSessionListener{
	@Override
	public void sessionCreated(HttpSessionEvent arg) {
		HttpSession session = arg.getSession();
		
		SessionUtil.saveSession(session);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg) {
		HttpSession session = arg.getSession();
		
		SessionUtil.removeSession(session);
	}
}