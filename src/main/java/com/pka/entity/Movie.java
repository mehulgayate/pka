package com.pka.entity;

import javax.persistence.Entity;

import com.evalua.entity.support.EntityBase;

@Entity
public class Movie extends EntityBase{
	
	public enum ServerStatus{
		ACTIVE,SUSPENDED,INACTIVE;
	}
	
	
	
	private String name;
	private ServerStatus status;
	private String discription;
	
	
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

}
