package com.pka.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.User;
import com.pka.entity.support.Repository;


@Controller
public class UserController {

	@Resource
	private Repository repository;
	
	@Resource
	private DataStoreManager dataStoreManager;
	
	@RequestMapping("/login")
	public ModelAndView showLogin(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/authenticate")
	public ModelAndView authenticate(@RequestParam String email, @RequestParam String password){
		
		User user=repository.findUserByEmail(email);
		
		if(user!=null && user.getPassword().equals(password)){
			return new ModelAndView("redirect:/home");			
		}else{
			return new ModelAndView("loging-failure");
		}
	}
	
	@RequestMapping("/register")
	public ModelAndView showRegister(){
		return new ModelAndView("register");
	}
	
	@RequestMapping("/add-user")
	public ModelAndView addUser(@ModelAttribute User user){
		
		User foundUser=repository.findUserByEmail(user.getEmail());
		
		if(foundUser==null){
			dataStoreManager.save(user);
		}
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping("/home")
	public ModelAndView showHome(HttpServletRequest request){
		
		
		return new ModelAndView("home");
	}
	
}
