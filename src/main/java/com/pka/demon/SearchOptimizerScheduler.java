package com.pka.demon;

import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SearchOptimizerScheduler {
	
private Timer timer;
	
	private Logger logger = LoggerFactory.getLogger(SearchOptimizerScheduler.class);
	
	private SearchOptimizerDemon searchOptimizerDemon;	
	public void setSearchOptimizerDemon(SearchOptimizerDemon searchOptimizerDemon) {
		this.searchOptimizerDemon = searchOptimizerDemon;
	}




	void init() {
		timer = new Timer();
		
		//Start after 10 seconds of booting up the webserver and then run for every 10 seconds
		timer.schedule(searchOptimizerDemon, 20*1000, 15*60*1000);
		
		if(logger.isInfoEnabled()){
			logger.info("Search Optimizer timer started");
		}
	}

}
