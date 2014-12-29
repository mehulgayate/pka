package com.pka.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import net.sf.json.JSONObject;

import com.evalua.entity.support.EntityBase;

@Entity
public class Movie extends EntityBase{
	
	public enum ServerStatus{
		ACTIVE,SUSPENDED,INACTIVE;
	}	
	
	private String name;
	private ServerStatus status;
	private String discription;
	private String imageUrl;
	private Long hitScore=0L;	
	
	public Long getHitScore() {
		return hitScore;
	}
	public void setHitScore(Long hitScore) {
		this.hitScore = hitScore;
	}
	public ServerStatus getStatus() {
		return status;
	}
	public void setStatus(ServerStatus status) {
		this.status = status;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public JSONObject toJSON(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", this.getId());
		jsonObject.put("name", this.name);
		jsonObject.put("discription", this.discription);
		jsonObject.put("imageUrl", imageUrl);
		
		return jsonObject; 
	}
	
	public static List<JSONObject> listToJSON(List<Movie> movies){
		List<JSONObject> jsonObjects=new ArrayList<JSONObject>();		
		for (Movie movie : movies) {
			jsonObjects.add(movie.toJSON());
		}
		
		return jsonObjects;
	}
	
}
