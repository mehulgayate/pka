package com.pka.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.GraphData;
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
	
	@RequestMapping("/admin/add-movie")
	public ModelAndView addNewServer(HttpSession httpSession, @ModelAttribute Movie movie){
		ModelAndView mv=new ModelAndView("redirect:/admin");		
		dataStoreManager.save(movie);		
		return mv;
	}	
	
	@RequestMapping("/admin/update-movie")
	public ModelAndView edit(HttpSession httpSession, @ModelAttribute Movie movie){
		ModelAndView mv=new ModelAndView("admin/edit-movie");
		Movie existMovie = repository.findMovieById(movie.getId());
		existMovie.setDiscription(movie.getDiscription());
		existMovie.setImageUrl(movie.getImageUrl());
		existMovie.setName(movie.getName());
		dataStoreManager.save(existMovie);		
		return mv;
	}
	
	@RequestMapping("/admin/edit-movie")
	public ModelAndView edit(HttpSession httpSession, @RequestParam Long id){
		ModelAndView mv=new ModelAndView("admin/edit-movie");
		Movie existMovie = repository.findMovieById(id);	
				
		return mv.addObject("movie", existMovie);
	}
	
	@RequestMapping("/")
	public ModelAndView showSerach(){
		return new ModelAndView("search").addObject("latests",repository.listLatestAddedMovies());
	}
	
	@RequestMapping("/admin/upload-movies")
	public ModelAndView uploadBulk(){
		return new ModelAndView("admin/upload-movies");
	}
	
	@RequestMapping("/admin/graphs")
	public ModelAndView showGraph(){
		return new ModelAndView("admin/graph");
	}
	
	
	@RequestMapping("/admin/grapth-data")
	@ResponseBody
	public String getGraphData(){		
		List<GraphData> graphDatas=repository.listGraphData();
		return GraphData.listToJSON(graphDatas).toString();		
	}
	
	
}
