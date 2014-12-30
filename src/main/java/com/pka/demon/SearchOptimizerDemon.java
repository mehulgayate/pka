package com.pka.demon;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.MetaData;
import com.pka.entity.Movie;
import com.pka.entity.support.Repository;

public class SearchOptimizerDemon extends TimerTask{


	public static Logger logger = LoggerFactory.getLogger(SearchOptimizerDemon.class);

	public static Timer sessionTimer = new Timer("Serarch Optimizer", true);   

	private Repository repository;
	private DataStoreManager dataStoreManager;
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public void setDataStoreManager(DataStoreManager dataStoreManager) {
		this.dataStoreManager = dataStoreManager;
	}

	@Override
	public void run() {

		logger.info("Running Serarch Optimizer Demon *******");
		
		List<MetaData> metaDatas=repository.listAllMetaData();
		
		for (MetaData metaData : metaDatas) {
			List<Movie> movies=repository.listMoviesBySearchString(metaData.getKeyword());
			for (Movie movie : movies) {
				metaData.addMovie(movie);
			}
			dataStoreManager.save(metaData);
		}		
	}
}
