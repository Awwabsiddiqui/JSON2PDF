package com.example.springrest.controller;

import java.io.IOException;

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
				"http://localhost:8080/json2pdf?fileName=" };
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

}
