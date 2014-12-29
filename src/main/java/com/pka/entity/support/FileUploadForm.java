package com.pka.entity.support;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

	public static final String key="fileUploadForm";
	
	private MultipartFile csvFile;

	public MultipartFile getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(MultipartFile csvFile) {
		this.csvFile = csvFile;
	}

	
}
