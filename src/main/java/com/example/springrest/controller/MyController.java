package com.example.springrest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrest.method.json2pdfTable;

//@RequestMapping(method = RequestMethod.X)  where X = {GET , PUT , POST DELETE ETC.} === @GetMapping , @PostMapping

@RestController
public class MyController {

	@GetMapping("/")
	public String[] homepage() {
		String[] arr = new String[] { "http://localhost:8080", "http://localhost:8080/json2pdf",
				"http://localhost:8080/json2pdf?fileName=", "http://localhost:8080/download?fileName=" };
		return arr;
	}

	@PostMapping("/json2pdf")
	public String config(@RequestBody String json,
			@RequestParam(required = false, defaultValue = "PDF", value = "fileName") String fileName)
			throws IOException {
		String status = json2pdfTable.json2pdf(json, fileName);
		return "File name : " + status;
	}

	@GetMapping("/json2pdf")
	public String config2(@RequestBody String json,
			@RequestParam(required = false, defaultValue = "PDF", value = "fileName") String fileName)
			throws IOException {
		String status = json2pdfTable.json2pdf(json, fileName);
		return "File name : " + status;
	}

	private static final String EXTENSION = ".pdf";

//	@RequestMapping(path = "/download", method = RequestMethod.GET)
	@GetMapping("/download")
	public ResponseEntity<ByteArrayResource> download(@RequestParam("fileName") String fileName) throws IOException {
		File file = new File(fileName + EXTENSION);

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PDF.pdf");
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().headers(header).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
	}
}
