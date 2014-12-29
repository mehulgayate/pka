package com.pka.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evalua.entity.support.DataStoreManager;
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
		
		return Movie.listToJSON(movies).toString();
	}
	
	@ResponseBody
	@RequestMapping("/movie")
	public String showMovie(@RequestParam Long id){
		
		Movie movie=repository.findMovieById(id);
		movie.setHitScore(movie.getHitScore()+1);
		dataStoreManager.save(movie);
		return movie.toJSON().toString();
	}
	
}
