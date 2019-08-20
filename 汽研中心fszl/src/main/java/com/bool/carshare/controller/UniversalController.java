/**
 * 
 */
package com.bool.carshare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bool.carshare.service.IUserService;

/**
 * UniversalController
 * @author wangw
 */
@RestController
@RequestMapping("boolcarshare")
public class UniversalController extends UniversalAbstractController{
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="getjsontest",method=RequestMethod.POST)
	public void getJSONTest(HttpServletRequest request, HttpServletResponse response) {
		this.initController(request, response);
		
		this.invokeMethod(IUserService.class, this.userService);
	}
}