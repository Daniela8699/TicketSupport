package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medved.support.logic.implementations.KeywordService;

@RestController
@RequestMapping("/medvedapi/keywords")
@CrossOrigin(origins = "*")
public class KeywordRestController {

	private KeywordService service;

	@Autowired
	public KeywordRestController(KeywordService service) {
		this.service = service;
	}

	@GetMapping("/topKeywords/{month}")
	public String findKeywords(@PathVariable("month") int month) {
		return service.getTopNKeywords(10, month);
	}

}
