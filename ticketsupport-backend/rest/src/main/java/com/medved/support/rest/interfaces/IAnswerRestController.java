package com.medved.support.rest.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.medved.support.model.Answer;

public interface IAnswerRestController {
	public Answer findById(@PathVariable("id") long id);

	public Iterable<Answer> findAll();

	public void save(@RequestBody Answer serv);
	
	public void updateEntity(@RequestBody Answer answer);

	public void remove(long id);

	public void removeState(long id);

}
