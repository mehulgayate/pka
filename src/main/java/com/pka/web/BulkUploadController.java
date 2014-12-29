package com.pka.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.evalua.entity.support.DataStoreManager;
import com.pka.entity.Movie;
import com.pka.entity.User;
import com.pka.entity.support.FileUploadForm;
import com.pka.entity.support.Repository;



@Controller
public class BulkUploadController {

	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;

	@Autowired
	ServletContext servletContext;

	@RequestMapping("/admin/upload-bulk")
	public ModelAndView showLogin(@ModelAttribute(FileUploadForm.key) FileUploadForm fileUploadForm) throws IOException{	


		try {

			File csvFile = new File(servletContext.getRealPath("WEB-INF/classes/csv/movie.csv"));
			if(csvFile.exists()){
				csvFile.delete();
			}
			
			System.out.println("***** Creating file at "+servletContext.getRealPath("WEB-INF/classes/csv/movie.csv"));
			new File(servletContext.getRealPath("WEB-INF/classes/csv/")).mkdirs();
			csvFile.createNewFile();

			// read this file into InputStream
			fileUploadForm.getCsvFile().transferTo(new File(servletContext.getRealPath("WEB-INF/classes/csv/movie.csv")));

			
			System.out.println("********** File Creation Done!");

			// Now Parse CSV

			ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
			strat.setType(Movie.class);
			String[] columns = new String[] { "name", "discription", "imageUrl" };
			strat.setColumnMapping(columns);

			CsvToBean csv = new CsvToBean();


			URL url = BulkUploadController.class.getResource("/csv/movie.csv");

			CSVReader reader = new CSVReader(new FileReader(url.getFile()));
			List<Movie> list = csv.parse(strat, reader);

			for (Movie movie : list) {
				if(repository.findMovieByName(movie.getName())==null){
					dataStoreManager.save(movie);
				}
			}

		}catch(Exception exception){
			exception.printStackTrace();
		}
		return new ModelAndView("redirect:/admin");
	}



}
