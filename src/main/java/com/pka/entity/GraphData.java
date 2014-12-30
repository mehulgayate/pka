package com.pka.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import net.sf.json.JSONObject;

import com.evalua.entity.support.EntityBase;

@Entity
public class GraphData extends EntityBase{	

	private Long pkaTime;
	private Long normalTime;
	private Date date;	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getPkaTime() {
		return pkaTime;
	}
	public void setPkaTime(Long pkaTime) {
		this.pkaTime = pkaTime;
	}
	public Long getNormalTime() {
		return normalTime;
	}
	public void setNormalTime(Long normalTime) {
		this.normalTime = normalTime;
	}
	
	public JSONObject toJSON(){
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("time", this.date.toString());
		jsonObject.put("pkaTime", this.pkaTime);
		jsonObject.put("normalTime", this.normalTime);
		return jsonObject;
	}
	
	public static List<JSONObject> listToJSON(List<GraphData> graphDatas){
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		
		for (GraphData graphData : graphDatas) {
			jsonObjects.add(graphData.toJSON());
		}
		
		return jsonObjects;
	}
	
		
}
