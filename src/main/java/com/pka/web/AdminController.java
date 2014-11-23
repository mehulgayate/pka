package com.pka.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.Movie;
import com.pka.entity.support.Repository;


@Controller
public class AdminController {
	
	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private Repository repository;
	
	
	@RequestMapping("/admin")
	public ModelAndView login(HttpSession httpSession){
		ModelAndView mv=new ModelAndView("admin/index");
		
		mv.addObject("movies", repository.listAllMovies());
		return mv;
	}
	
	@RequestMapping("/admin/add-new-movie")
	public ModelAndView showAddNew(HttpSession httpSession){
		ModelAndView mv=new ModelAndView("admin/add-new-movie");
		
		return mv;
	}
	
	/*@RequestMapping("/admin/edit-server")
	public ModelAndView showeEditNew(HttpSession httpSession,@RequestParam Long id){
		ModelAndView mv=new ModelAndView("admin/edit-server");
		Server server=repository.findServerById(id);
		mv.addObject("server", server);
		
		return mv;
	}*/
	
	
	
	@RequestMapping("/admin/add-movie")
	public ModelAndView addNewServer(HttpSession httpSession, @ModelAttribute Movie movie){
		ModelAndView mv=new ModelAndView("redirect:/admin");		
		dataStoreManager.save(movie);		
		
		
		
		return mv;
	}
	
	/*@RequestMapping("/admin/update-server")
	public ModelAndView editServer(HttpSession httpSession, @ModelAttribute Movie server){
		ModelAndView mv=new ModelAndView("redirect:/admin");
		Movie toUpdateServer=repository.findServerById(server.getId());
		toUpdateServer.setCapacityThreshold(server.getCapacityThreshold());
		toUpdateServer.setIp(server.getIp());
		toUpdateServer.setMigrationActive(server.isMigrationActive());
		toUpdateServer.setName(server.getName());
		toUpdateServer.setPortNumber(server.getPortNumber());
		toUpdateServer.setRequestCapacity(server.getRequestCapacity());
		toUpdateServer.setStatus(server.getStatus());
		dataStoreManager.save(toUpdateServer);		
		
		return mv;
	}*/
	
	
}
