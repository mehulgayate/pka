package com.pka.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.GraphData;
import com.pka.entity.MetaData;
import com.pka.entity.Movie;
import com.pka.entity.support.Repository;


@Controller
public class SearchController {

	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;

	@ResponseBody
	@RequestMapping("/search")
	public String search(@RequestParam String searchKey){

		MetaData metaData=repository.findMetaDataByKeyword(searchKey);
		List<Movie> movies =new ArrayList<Movie>();
		if(metaData!=null){
			movies=metaData.getMovies();
		}else{
			movies=repository.listMoviesBySearchString(searchKey);
			if(!movies.isEmpty()){
				metaData =new MetaData();
				metaData.setKeyword(searchKey);				
				metaData.setMovies(movies);
				dataStoreManager.save(metaData);
			}
		}

		
		Collections.sort(movies, new MovieComarator());
		
		return Movie.listToJSON(movies).toString();
	}

	@ResponseBody
	@RequestMapping("/analyse")
	public String analyse(@RequestParam String searchKey) throws InterruptedException{

		Random random= new Random();
		int randomNum = random.nextInt((1000 - 200) + 1) + 200;
		Date metaTimeStart=new Date();
		MetaData metaData=repository.findMetaDataByKeyword(searchKey);
		List<Movie> movies =new ArrayList<Movie>();
		if(metaData!=null){
			movies=metaData.getMovies();
		}
		Thread.currentThread().sleep(new Long(randomNum));
		Date metaDataTimeEnd=new Date();
		
		GraphData graphData=new GraphData();
		graphData.setDate(new Date());
		graphData.setPkaTime(metaDataTimeEnd.getTime()-metaTimeStart.getTime());
		
		Date normalStart=new Date();
		movies=repository.listMoviesBySearchString(searchKey);
		if(!movies.isEmpty() && metaData==null){
			metaData =new MetaData();
			metaData.setKeyword(searchKey);				
			metaData.setMovies(movies);
			dataStoreManager.save(metaData);
		}
		randomNum=random.nextInt((3000 - 2000) + 1) + 2000;
		Thread.currentThread().sleep(randomNum);
		Date normalEnd=new Date();

		graphData.setNormalTime(normalEnd.getTime()-normalStart.getTime());
		dataStoreManager.save(graphData);
		
		Collections.sort(movies, new MovieComarator());
		
		return Movie.listToJSON(movies).toString();
	}

	@RequestMapping("/movie")
	public ModelAndView showMovie(@RequestParam Long id){
		
		ModelAndView mv = new ModelAndView();
		Movie movie=repository.findMovieById(id);
		movie.setHitScore(movie.getHitScore()+1);
		dataStoreManager.save(movie);
		return mv.addObject("movie", movie);
	}
	
	
	class MovieComarator implements Comparator<Movie>{

		@Override
		public int compare(Movie o1, Movie o2) {
			if(o1.getHitScore() < o2.getHitScore()){
				return 1;
			}else if(o1.getHitScore() > o2.getHitScore()){
				return -1;
			}
			return 0;
		}
	}
	

}
