package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.ISourceService;
import com.medved.support.model.Source;
import com.medved.support.rest.interfaces.ISourceRestController;


@RequestMapping("/medvedapi/sources")
@RestController
@CrossOrigin(origins="*")
public class SourceRestController implements ISourceRestController {

	@Autowired
	private ISourceService service;
	
	@Override
	@GetMapping("/{id}")
	public Source findById(@PathVariable("id")Long sourceId) {
		try {
			return service.findById(sourceId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	@GetMapping("")
	public Iterable<Source> findAll() {
		return service.findAll();
	}

	@Override
	@PostMapping("")
	public void save(Source source) {

		try {
			service.save(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@PutMapping("")
	public void updateEntity(Source source) {
		service.update(source);
	}

	@Override
	@DeleteMapping("/{id}")
	public void remove(Long sourceId) {
		Source a = service.findById(sourceId);
		if (a != null)
			service.remove(a);
	}

}
