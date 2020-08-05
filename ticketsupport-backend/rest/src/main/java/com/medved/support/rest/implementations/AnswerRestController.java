package com.medved.support.rest.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medved.support.logic.interfaces.IAnswerService;
import com.medved.support.model.Answer;
import com.medved.support.rest.interfaces.IAnswerRestController;

@RequestMapping("/medvedapi/answers")
@RestController
@CrossOrigin(origins="*")
public class AnswerRestController implements IAnswerRestController{

	@Autowired
	private IAnswerService service;

	@GetMapping("/{id}")
	public Answer findById(@PathVariable("id") long id) {

		try {
			return service.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("")
	public Iterable<Answer> findAll() {
		return service.findAll();
	}

	@PostMapping("")
	public void save(@RequestBody Answer serv) {

		try {
			serv.setId(System.nanoTime());
			service.save(serv);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@PutMapping("")
	public void updateEntity(@RequestBody Answer answer) {
		service.update(answer);
	}

	@DeleteMapping("/{id}")
	public void remove(long id) {
		Answer a = service.findById(id);
		if (a != null)
			service.remove(a);
	}

	@DeleteMapping("/state/{id}")
	public void removeState(long id) {
		Answer a = service.findById(id);
		if (a != null)
			service.removeState(a);
	}
}
