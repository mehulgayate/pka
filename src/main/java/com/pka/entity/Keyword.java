package com.pka.entity;

import javax.persistence.Entity;

import com.evalua.entity.support.EntityBase;

@Entity
public class Keyword extends EntityBase{
	
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
