package udd.controllers;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@RestController
@RequestMapping(value="/downloadPdf")
public class PdfController {
	
	
	@RequestMapping(value = "/{fileName}", method = RequestMethod.GET)
	public HttpEntity<byte[]> createPdf(@PathVariable String fileName) throws IOException {
		Path pdfPath = Paths.get("src/main/java/udd/elastic/" + fileName.replace("_", " ") +".pdf");
		
	    byte[] documentBody = Files.readAllBytes(pdfPath);

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.APPLICATION_PDF);
	    header.set(HttpHeaders.CONTENT_DISPOSITION,
	                   "attachment; filename=" + fileName.replace(" ", "_") + ".pdf");
	    header.setContentLength(documentBody.length);

	    return new HttpEntity<byte[]>(documentBody, header);
	}
}
